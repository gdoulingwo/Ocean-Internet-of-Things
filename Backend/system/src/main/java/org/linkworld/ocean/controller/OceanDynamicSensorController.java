package org.linkworld.ocean.controller;


import org.linkworld.ocean.service.OceanDynamicSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态的传感器，例如船舶中的GPS 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-11-04
 */
@RestController
@RequestMapping("/ocean-dynamic-sensor")
public class OceanDynamicSensorController {
    @Autowired
    private OceanDynamicSensorService oceanDynamicSensorService;
}

