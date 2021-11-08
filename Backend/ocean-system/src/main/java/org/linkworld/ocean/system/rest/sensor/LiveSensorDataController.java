package org.linkworld.ocean.system.rest.sensor;

import org.linkworld.ocean.system.common.ResultBean;
import org.linkworld.ocean.system.service.OceanSensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HALOXIAO
 * @since 2021-11-08 00:05
 **/
@RestController
@RequestMapping("/live")
public class LiveSensorDataController {


    @Autowired
    private OceanSensorDataService sensorDataService;


    @GetMapping("/")
    public ResultBean getSensorData(@RequestParam("config_id") Long configId) {
        sensorDataService.queryDataRecently(configId);
        return new ResultBean<>();
    }

}
