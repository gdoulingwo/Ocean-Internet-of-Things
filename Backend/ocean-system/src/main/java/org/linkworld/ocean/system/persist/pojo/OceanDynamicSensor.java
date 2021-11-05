package org.linkworld.ocean.system.persist.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 动态的传感器，例如船舶中的GPS
 * </p>
 *
 * @author ${author}
 * @since 2021-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "OceanDynamicSensor对象", description = "动态的传感器，例如船舶中的GPS")
public class OceanDynamicSensor implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String config;

    @ApiModelProperty(value = "注释")
    private String note;


}
