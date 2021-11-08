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
package org.linkworld.ocean.system.persist.module;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author HALOXIAO
 * @date 2021-11-06
 **/
@Entity
@Data
@Table(name = "ocean_sensor")
public class OceanSensor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "name")
    private String name;

    @Column(name = "user_id", nullable = false)
    @NotNull
    @ApiModelProperty(value = "userId")
    private Long userId;

    @Column(name = "config", nullable = false)
    @NotNull
    @ApiModelProperty(value = "用户的配置")
    private String config;

    @Column(name = "position")
    @ApiModelProperty(value = "传感器的位置（用户设定）,如果是GPS类型的话，直接置空")
    private String position;

    @Column(name = "note")
    @ApiModelProperty(value = "注释")
    private String note;

    @Column(name = "topic")
    @ApiModelProperty(value = "mqtt topic")
    @NotNull
    private String topic;

    @Column(name = "create_time", nullable = false)
    @NotNull
    @CreationTimestamp
    @ApiModelProperty(value = "createTime")
    private Timestamp createTime;

    public void copy(OceanSensor source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}