package org.linkworld.ocean.system.rest.sensor;

import io.swagger.annotations.ApiOperation;
import me.zhengjie.annotation.AnonymousAccess;
import me.zhengjie.annotation.rest.AnonymousGetMapping;
import org.linkworld.ocean.system.common.ResultBean;
import org.linkworld.ocean.system.persist.vo.SensorCoordinateVO;
import org.linkworld.ocean.system.service.OceanSensorDataService;
import org.linkworld.ocean.system.service.OceanSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * @author HALOXIAO
 * @since 2021-11-08 00:05
 **/
@RestController
@RequestMapping("/live")
public class LiveSensorDataController {


    @Autowired
    private OceanSensorDataService sensorDataService;

    @Autowired
    private OceanSensorService oceanSensorService;

    @AnonymousGetMapping("/sensor/data")
//    @AnonymousAccess
//    @PermitAll
    @ApiOperation("获取具体某个传感器的数据")
    public ResultBean getSensorData(@RequestParam("config_id") Long configId) {
        ResultBean bean = ResultBean.OK();
        bean.setData(sensorDataService.queryDataRecently(configId));
        return bean;
    }

    @AnonymousGetMapping("/sensor")
    @ApiOperation("获取所有传感器的数据")
//    @AnonymousAccess
//    @PermitAll
    public ResultBean querySensorCoordinate() {
        List<SensorCoordinateVO> result = oceanSensorService.querySensorCoordinate();
        ResultBean bean = ResultBean.OK();
        bean.setData(result);
        return bean;
    }
    
}
