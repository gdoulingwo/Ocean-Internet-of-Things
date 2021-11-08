package org.linkworld.ocean.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.linkworld.ocean.system.persist.module.OceanSensorData;
import org.linkworld.ocean.system.persist.vo.SensorDataVO;

/**
 *
 */
public interface OceanSensorDataService extends IService<OceanSensorData> {

    SensorDataVO queryDataRecently(Long configId);

}
