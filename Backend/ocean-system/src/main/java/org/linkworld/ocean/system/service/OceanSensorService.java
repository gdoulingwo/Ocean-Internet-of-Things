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
package org.linkworld.ocean.system.service;

import org.linkworld.ocean.system.persist.module.OceanSensor;
import org.linkworld.ocean.system.persist.vo.SensorCoordinateVO;
import org.linkworld.ocean.system.service.dto.OceanSensorDto;
import org.linkworld.ocean.system.service.dto.OceanSensorQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author HALOXIAO
 * @website https://el-admin.vip
 * @description 服务接口
 * @date 2021-11-06
 **/
public interface OceanSensorService {

    /**
     * 查询数据分页
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String, Object>
     */
    Map<String, Object> queryAll(OceanSensorQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     *
     * @param criteria 条件参数
     * @return List<OceanSensorDto>
     */
    List<OceanSensorDto> queryAll(OceanSensorQueryCriteria criteria);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return OceanSensorDto
     */
    OceanSensorDto findById(Long id);

    /**
     * 创建
     *
     * @param resources /
     * @return OceanSensorDto
     */
    OceanSensorDto create(OceanSensor resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(OceanSensor resources);

    /**
     * 多选删除
     *
     * @param ids /
     */
    void deleteAll(Long[] ids);

    /**
     * 导出数据
     *
     * @param all      待导出的数据
     * @param response /
     * @throws IOException /
     */

    /**
     * 分页查询
     *
     * @param pageNumber 当前页码
     * @param pageSize   每页的记录数
     * @return List<OceanSensor>
     */
    List<OceanSensor> queryOceanSensorByPage(int pageNumber, int pageSize);


    List<SensorCoordinateVO> querySensorCoordinate();


}