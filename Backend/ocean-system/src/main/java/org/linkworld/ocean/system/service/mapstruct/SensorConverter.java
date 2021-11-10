package org.linkworld.ocean.system.service.mapstruct;

import jdk.jfr.TransitionFrom;
import org.linkworld.ocean.system.common.CoordinateTransformUtil;
import org.linkworld.ocean.system.persist.module.OceanSensor;
import org.linkworld.ocean.system.persist.vo.SensorCoordinateVO;

/**
 * @author HALOXIAO
 * @since 2021-11-09 16:18
 **/
public class SensorConverter {

    public static SensorCoordinateVO toSensorCoordinateVO(OceanSensor oceanSensor) {
        SensorCoordinateVO coordinateVO = new SensorCoordinateVO();
        coordinateVO.setId(oceanSensor.getId());

        coordinateVO.setLatitude(oceanSensor.getLatitude());
        coordinateVO.setLongitude(oceanSensor.getLongitude());
        coordinateVO.setName(oceanSensor.getName());
        return coordinateVO;
    }

}
