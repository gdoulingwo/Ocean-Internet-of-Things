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
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.linkworld.ocean.system.manager.MqttTopicHandler;
import org.linkworld.ocean.system.persist.module.OceanSensor;
import org.linkworld.ocean.system.repository.OceanSensorRepository;
import org.linkworld.ocean.system.service.OceanSensorService;
import org.linkworld.ocean.system.service.dto.OceanSensorDto;
import org.linkworld.ocean.system.service.dto.OceanSensorQueryCriteria;
import org.linkworld.ocean.system.service.mapstruct.OceanSensorMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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
        if (!JSONUtil.isJson(resources.getConfig())) {
            throw new IllegalArgumentException();
        }

        return oceanSensorMapper.toDto(oceanSensorRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(OceanSensor resources) {
        OceanSensor oceanSensor = oceanSensorRepository.findById(resources.getId()).orElseGet(OceanSensor::new);
        ValidationUtil.isNull(oceanSensor.getId(), "OceanSensor", "id", resources.getId());
        oceanSensor.copy(resources);
        oceanSensorRepository.save(oceanSensor);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            oceanSensorRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<OceanSensorDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (OceanSensorDto oceanSensor : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put(" name", oceanSensor.getName());
            map.put(" userId", oceanSensor.getUserId());
            map.put("用户的配置", oceanSensor.getConfig());
            map.put("传感器的位置（用户设定）,如果是GPS类型的话，直接置空", oceanSensor.getPosition());
            map.put("注释", oceanSensor.getNote());
            map.put(" createTime", oceanSensor.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}