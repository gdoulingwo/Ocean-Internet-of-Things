/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package org.linkworld.ocean.system.rest.sensor;

import org.linkworld.ocean.system.persist.module.OceanSensor;
import org.linkworld.ocean.system.service.OceanSensorService;
import org.linkworld.ocean.system.service.dto.OceanSensorQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @author HALOXIAO
* @date 2021-11-06
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "Sensor管理")
@RequestMapping("/api/oceanSensor")
public class OceanSensorController {

    private final OceanSensorService oceanSensorService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('oceanSensor:list')")
    public void download(HttpServletResponse response, OceanSensorQueryCriteria criteria) throws IOException {
        oceanSensorService.download(oceanSensorService.queryAll(criteria), response);
    }

    @GetMapping
    @ApiOperation("查询Sensor")
    @PreAuthorize("@el.check('oceanSensor:list')")
    public ResponseEntity<Object> query(OceanSensorQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(oceanSensorService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("新增Sensor")
    @PreAuthorize("@el.check('oceanSensor:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody OceanSensor resources){
        return new ResponseEntity<>(oceanSensorService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation("修改Sensor")
    @PreAuthorize("@el.check('oceanSensor:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody OceanSensor resources){
        oceanSensorService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("删除Sensor")
    @PreAuthorize("@el.check('oceanSensor:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        oceanSensorService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}