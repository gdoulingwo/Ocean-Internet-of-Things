package org.linkworld.ocean.controller;


import org.linkworld.ocean.service.OceanSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-11-04
 */
@RestController
@RequestMapping("/ocean-sensor")
public class OceanSensorController {
    @Autowired
    private OceanSensorService oceanSensorService;
}

