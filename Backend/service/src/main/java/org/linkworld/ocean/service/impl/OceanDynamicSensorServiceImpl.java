package org.linkworld.ocean.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.linkworld.ocean.mapper.OceanDynamicSensorMapper;
import org.linkworld.ocean.model.pojo.OceanDynamicSensor;
import org.linkworld.ocean.model.pojo.OceanSensor;
import org.linkworld.ocean.service.OceanDynamicSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 动态的传感器，例如船舶中的GPS 服务实现类
 * </p>
 *
 * @author LiuXiangCheng
 * @since 2021-11-04
 */
@Service
public class OceanDynamicSensorServiceImpl extends ServiceImpl<OceanDynamicSensorMapper, OceanDynamicSensor> implements OceanDynamicSensorService {
    @Autowired
    private OceanDynamicSensorMapper oceanDynamicSensorMapper;


    @Override
    public Integer insertOceanDynamicSensor(OceanDynamicSensor oceanDynamicSensor) {
        return oceanDynamicSensorMapper.insert(oceanDynamicSensor);
    }

    @Override
    public Integer deleteOceanDynamicSensorById(int oceanDynamicSensorId) {
        return oceanDynamicSensorMapper.deleteById(oceanDynamicSensorId);
    }

    @Override
    public Integer updateOceanDynamicSensorById(OceanDynamicSensor oceanDynamicSensor) {
        return oceanDynamicSensorMapper.updateById(oceanDynamicSensor);
    }

    @Override
    public OceanDynamicSensor selectOceanDynamicSensorById(int oceanDynamicSensorId) {
        return oceanDynamicSensorMapper.selectById(oceanDynamicSensorId);
    }

    @Override
    public List<OceanDynamicSensor> selectOceanDynamicSensorList() {
        return oceanDynamicSensorMapper.selectList(null);
    }

    @Override
    public Integer OceanDynamicSensorsCount() {
        return oceanDynamicSensorMapper.selectCount(null);
    }

    @Override
    public List<OceanDynamicSensor> selectOceanSensorByPage(int pageNumber, int pageSize) {
        Page<OceanDynamicSensor> page = new Page<>(pageNumber,pageSize);
        // 最大每页分页数限制,优先级高于分页插件内的 maxLimit,如果输入的pageSize超过20，则自动替换为20
        page.setMaxLimit(20L);
        Page<OceanDynamicSensor> oceanSensorPage = oceanDynamicSensorMapper.selectPage(page, null);
        return oceanSensorPage.getRecords();

    }
}
