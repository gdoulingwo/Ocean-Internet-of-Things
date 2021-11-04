package org.linkworld.ocean.pojo;

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
 * 
 * </p>
 *
 * @author ${author}
 * @since 2021-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OceanSensor对象", description="")
public class OceanSensor implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer userId;

    @ApiModelProperty(value = "用户的配置")
    private String config;

    @ApiModelProperty(value = "传感器的位置（用户设定）")
    private String position;

    @ApiModelProperty(value = "注释")
    private String note;


}
