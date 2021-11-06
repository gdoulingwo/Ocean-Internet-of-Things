package org.linkworld.ocean.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.linkworld.ocean.mapper.OceanSensorMapper;
import org.linkworld.ocean.model.pojo.OceanSensor;
import org.linkworld.ocean.service.OceanSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LiuXiangCheng
 * @since 2021-11-04
 */
@Service
public class OceanSensorServiceImpl extends ServiceImpl<OceanSensorMapper, OceanSensor> implements OceanSensorService {
    @Autowired
    private OceanSensorMapper oceanSensorMapper;

    @Override
    public Integer insertOceanSensor(OceanSensor oceanSensor) {
        return oceanSensorMapper.insert(oceanSensor);
    }

    @Override
    public Integer deleteOceanSensorById(int oceanSensorId) {
        return oceanSensorMapper.deleteById(oceanSensorId);
    }

    @Override
    public Integer updateOceanSensorById(OceanSensor oceanSensor) {
        return oceanSensorMapper.updateById(oceanSensor);
    }

    @Override
    public OceanSensor selectOceanSensorById(int oceanSensorId) {
        return oceanSensorMapper.selectById(oceanSensorId);
    }

    @Override
    public List<OceanSensor> selectOceanSensorList() {
        return oceanSensorMapper.selectList(null);
    }

    @Override
    public Integer OceanSensorsCount() {
        return oceanSensorMapper.selectCount(null);
    }
}
