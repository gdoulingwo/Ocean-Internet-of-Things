package org.linkworld.ocean.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.linkworld.ocean.model.pojo.OceanSensor;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuXiangCheng
 * @since 2021-11-04
 */
public interface OceanSensorService extends IService<OceanSensor> {
    // 增加一个传感器
    public Integer insertOceanSensor(OceanSensor oceanSensor);
    // 根据id删除一个传感器
    public Integer deleteOceanSensorById(int oceanSensorId);
    // 修改一个传感器的信息
    public Integer updateOceanSensorById(OceanSensor oceanSensor);
    // 通过id查询一个传感器
    public OceanSensor selectOceanSensorById(int oceanSensorId);
    // 查询多个传感器的信息
    public List<OceanSensor> selectOceanSensorList();
    // 查询总传感器个数
    public Integer OceanSensorsCount();
    // 分页查询
    public List<OceanSensor> selectOceanSensorByPage(int pageNumber, int pageSize);

}
