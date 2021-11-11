package org.linkworld.ocean.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.utils.SecurityUtils;
import me.zhengjie.utils.enums.DataScopeEnum;
import org.linkworld.ocean.system.dao.OceanSensorDataMapper;
import org.linkworld.ocean.system.dao.OceanSensorMapper;
import org.linkworld.ocean.system.persist.module.OceanSensor;
import org.linkworld.ocean.system.persist.module.OceanSensorData;
import org.linkworld.ocean.system.persist.vo.SensorDataVO;
import org.linkworld.ocean.system.service.OceanSensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 *
 */
@Service
@Slf4j
public class OceanSensorDataServiceImpl extends ServiceImpl<OceanSensorDataMapper, OceanSensorData> implements OceanSensorDataService {

    @Autowired
    private OceanSensorMapper oceanSensorMapper;


    @Override
    public SensorDataVO queryDataRecently(Long configId) {
        OceanSensor sensor = oceanSensorMapper.selectOne(Wrappers.<OceanSensor>lambdaQuery().eq(OceanSensor::getId, configId));
        if (sensor == null || sensor.getId() == null) {
            throw new IllegalArgumentException();
        }
//        Long userId = SecurityUtils.getCurrentUserId();
        Long userId = 1L;
        OceanSensorData sensorData = getOne(Wrappers.<OceanSensorData>lambdaQuery().eq(OceanSensorData::getUserId, userId).eq(OceanSensorData::getConfigId, sensor.getId()).orderByAsc(OceanSensorData::getId));
        SensorDataVO result = new SensorDataVO();
        String strData = sensorData.getData();
        String strConfig = sensor.getConfig();
        JSONObject dataSorted = new JSONObject(new LinkedHashMap<>());
        JSONObject configSorted = new JSONObject(new LinkedHashMap<>());
        JSONObject dataObject = JSON.parseObject(strData);
        JSONObject configObject = JSON.parseObject(strConfig);
        configObject.keySet().forEach(p -> {
            configSorted.put(p, configObject.get(p));
            dataSorted.put(p, dataObject.get(p));
        });
        result.setData(dataSorted.toJSONString());
        result.setMean(configSorted.toJSONString());

        result.setTopic(sensorData.getTopic());
        if (sensor.getLongitude() == null) {
            JSONObject jsonData = JSON.parseObject(sensorData.getData());
            result.setLongitude(jsonData.getObject("longitude", Double.class));
            result.setLatitude(jsonData.getObject("latitude", Double.class));
        } else {
            result.setLongitude(sensor.getLongitude());
            result.setLatitude(sensor.getLatitude());
        }


        return result;
    }


}




