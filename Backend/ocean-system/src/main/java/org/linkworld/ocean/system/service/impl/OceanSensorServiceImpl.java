/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.linkworld.ocean.system.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.linkworld.ocean.system.manager.MqttTopicHandler;
import org.linkworld.ocean.system.persist.module.OceanSensor;
import org.linkworld.ocean.system.persist.vo.SensorCoordinateVO;
import org.linkworld.ocean.system.persist.vo.SensorDataVO;
import org.linkworld.ocean.system.persist.vo.SensorVO;
import org.linkworld.ocean.system.repository.OceanSensorRepository;
import org.linkworld.ocean.system.service.OceanSensorDataService;
import org.linkworld.ocean.system.service.OceanSensorService;
import org.linkworld.ocean.system.service.dto.OceanSensorDto;
import org.linkworld.ocean.system.service.dto.OceanSensorQueryCriteria;
import org.linkworld.ocean.system.service.mapstruct.OceanSensorMapper;
import org.linkworld.ocean.system.service.mapstruct.SensorConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author HALOXIAO
 * @website https://el-admin.vip
 * @description 服务实现
 * @date 2021-11-06
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class OceanSensorServiceImpl implements OceanSensorService {

    private final OceanSensorRepository oceanSensorRepository;
    private final OceanSensorMapper oceanSensorMapper;
    @Autowired
    private org.linkworld.ocean.system.dao.OceanSensorMapper realOceanSensorMapper;

    @Autowired
    private final OceanSensorDataService oceanSensorDataService;

    private final MqttTopicHandler topicHandler;

    @Override
    public Map<String, Object> queryAll(OceanSensorQueryCriteria criteria, Pageable pageable) {
        Page<OceanSensor> page = oceanSensorRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(oceanSensorMapper::toDto));
    }

    @Override
    public List<OceanSensorDto> queryAll(OceanSensorQueryCriteria criteria) {
        return oceanSensorMapper.toDto(oceanSensorRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public OceanSensorDto findById(Long id) {
        OceanSensor oceanSensor = oceanSensorRepository.findById(id).orElseGet(OceanSensor::new);
        ValidationUtil.isNull(oceanSensor.getId(), "OceanSensor", "id", id);
        return oceanSensorMapper.toDto(oceanSensor);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OceanSensorDto create(OceanSensor resources) {
        resources.setUserId(1L);
        if (!JSONUtil.isJson(resources.getConfig())) {
            throw new IllegalArgumentException();
        }
        resources.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));

        OceanSensorDto result = oceanSensorMapper.toDto(oceanSensorRepository.save(resources));
        topicHandler.addTopic(resources.getTopic());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(OceanSensor resources) {

        OceanSensor oceanSensor = oceanSensorRepository.findById(resources.getId()).orElseGet(OceanSensor::new);
        ValidationUtil.isNull(oceanSensor.getId(), "OceanSensor", "id", resources.getId());
        oceanSensor.copy(resources);
        oceanSensorRepository.save(oceanSensor);
        topicHandler.addTopic(resources.getTopic());
    }

    @Override
    public void deleteAll(Long[] ids) {
        List<Long> idList = Arrays.stream(ids).collect(Collectors.toList());
        List<OceanSensor> sensorList = oceanSensorRepository.findAllById(idList);
        for (Long id : ids) {
            oceanSensorRepository.deleteById(id);
        }
        sensorList.forEach(sensor -> {
            topicHandler.removeTopic(sensor.getTopic());
        });


    }


    @Override
    public List<SensorVO> queryOceanSensorByPage(int pageNumber, int pageSize) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<OceanSensor> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageNumber, pageSize);
        // 限制最大的查询个数，若超过这个数量，则自动替换为设定的数量
        page.setMaxLimit(20L);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<OceanSensor> oceanSensorPage = realOceanSensorMapper.selectPage(page, null);
        List<OceanSensor> sensorList = oceanSensorPage.getRecords();
        List<SensorVO> result = new ArrayList<>(sensorList.size());
        sensorList.forEach(sensor -> {
            SensorVO sensorVO = new SensorVO();
            sensorVO.setId(sensor.getId());

            sensorVO.setTopic(sensor.getTopic());
            sensorVO.setConfig(JSON.parse(sensor.getConfig()));
            sensorVO.setName(sensor.getName());
            sensorVO.setNote(sensor.getNote());
            sensorVO.setLatitude(sensor.getLatitude());
            sensorVO.setLongitude(sensor.getLongitude());
            sensorVO.setCreateTime(sensor.getCreateTime());
            result.add(sensorVO);
        });
        return result;

    }

    @Override
    public List<SensorCoordinateVO> querySensorCoordinate() {
        List<OceanSensor> sensorList = oceanSensorRepository.findAll();
        List<SensorCoordinateVO> result = new LinkedList<>();
        Set<Long> configIdSet = new HashSet<>();
        Map<Long, OceanSensor> dynamicSensorMap = new HashMap<>();
        sensorList.forEach(sensor -> {
            if (sensor.getLatitude() == null) {
                configIdSet.add(sensor.getId());
                dynamicSensorMap.put(sensor.getId(), sensor);
            } else {
                result.add(SensorConverter.toSensorCoordinateVO(sensor));
            }
        });

        configIdSet.forEach(id -> {
            SensorDataVO dataVO = oceanSensorDataService.queryDataRecently(id);
            OceanSensor sensor = dynamicSensorMap.get(id);
            sensor.setLatitude(dataVO.getLatitude());
            sensor.setLongitude(dataVO.getLongitude());
            result.add(SensorConverter.toSensorCoordinateVO(sensor));
        });
        return result;
    }


    @Override
    public SensorVO queryOceanSensor(Long id) {
        OceanSensor sensor = oceanSensorRepository.findById(id).get();
        SensorVO sensorVO = new SensorVO();
        sensorVO.setId(sensor.getId());

        sensorVO.setTopic(sensor.getTopic());
        sensorVO.setConfig(JSON.parse(sensor.getConfig()));
        sensorVO.setName(sensor.getName());
        sensorVO.setNote(sensor.getNote());
        sensorVO.setLatitude(sensor.getLatitude());
        sensorVO.setLongitude(sensor.getLongitude());
        sensorVO.setCreateTime(sensor.getCreateTime());
        return sensorVO;
    }
}