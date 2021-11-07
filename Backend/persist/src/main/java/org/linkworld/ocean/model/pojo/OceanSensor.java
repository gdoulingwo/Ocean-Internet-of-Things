package org.linkworld.ocean.model.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2021-11-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OceanSensor对象", description="")
@Accessors(chain = true)
public class OceanSensor implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer userId;

    @ApiModelProperty(value = "用户的配置")
    private String config;

    @ApiModelProperty(value = "传感器的位置（用户设定）,如果是GPS类型的话，直接置空")
    private String position;

    @ApiModelProperty(value = "注释")
    private String note;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createTime;

    @ApiModelProperty(value = "mqtt topic")
    @TableField(fill = FieldFill.DEFAULT)
    private String topic;


}
