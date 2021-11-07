package org.linkworld.ocean.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.linkworld.ocean.model.pojo.OceanDynamicSensor;
import org.linkworld.ocean.model.pojo.OceanSensor;

import java.util.List;

/**
 * <p>
 * 动态的传感器，例如船舶中的GPS 服务类
 * </p>
 *
 * @author LiuXiangCheng
 * @since 2021-11-04
 */
public interface OceanDynamicSensorService extends IService<OceanDynamicSensor> {
    // 增加一个动态传感器
    public Integer insertOceanDynamicSensor(OceanDynamicSensor oceanDynamicSensor);
    // 根据id删除一个传感器
    public Integer deleteOceanDynamicSensorById(int oceanDynamicSensorId);
    // 修改一个传感器的信息
    public Integer updateOceanDynamicSensorById(OceanDynamicSensor oceanDynamicSensor);
    // 通过id查询一个传感器
    public OceanDynamicSensor selectOceanDynamicSensorById(int oceanDynamicSensorId);
    // 查询多个传感器的信息
    public List<OceanDynamicSensor> selectOceanDynamicSensorList();
    // 查询总传感器个数
    public Integer OceanDynamicSensorsCount();
    // 分页查询
    public List<OceanDynamicSensor> selectOceanSensorByPage(int pageNumber, int pageSize);
}
