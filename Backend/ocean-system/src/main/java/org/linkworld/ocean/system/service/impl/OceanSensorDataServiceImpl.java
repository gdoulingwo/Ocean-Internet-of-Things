package org.linkworld.ocean.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.utils.SecurityUtils;
import org.linkworld.ocean.system.dao.OceanSensorDataMapper;
import org.linkworld.ocean.system.dao.OceanSensorMapper;
import org.linkworld.ocean.system.persist.module.OceanSensor;
import org.linkworld.ocean.system.persist.module.OceanSensorData;
import org.linkworld.ocean.system.persist.vo.SensorDataVO;
import org.linkworld.ocean.system.service.OceanSensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        OceanSensor sensor = oceanSensorMapper.selectOne(Wrappers.<OceanSensor>lambdaQuery().select(OceanSensor::getId).eq(OceanSensor::getId, configId));
        if (sensor == null || sensor.getId() == null) {
            throw new IllegalArgumentException();
        }
        Long userId = SecurityUtils.getCurrentUserId();
        OceanSensorData sensorData = getOne(Wrappers.<OceanSensorData>lambdaQuery().eq(OceanSensorData::getUserId, userId).eq(OceanSensorData::getConfigId, sensor.getId()).orderByAsc(OceanSensorData::getId));
        SensorDataVO result = new SensorDataVO();
        result.setData(sensorData.getData());
        result.setTopic(sensorData.getTopic());
        result.setMean(sensor.getConfig());

        return result;
    }


}




