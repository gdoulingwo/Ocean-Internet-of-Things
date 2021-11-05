package org.linkworld.ocean.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.linkworld.ocean.system.dao.OceanDynamicSensorMapper;
import org.linkworld.ocean.system.persist.pojo.OceanDynamicSensor;
import org.linkworld.ocean.system.service.OceanDynamicSensorService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 动态的传感器，例如船舶中的GPS 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-04
 */
@Service
public class OceanDynamicSensorServiceImpl extends ServiceImpl<OceanDynamicSensorMapper, OceanDynamicSensor> implements OceanDynamicSensorService {

}
