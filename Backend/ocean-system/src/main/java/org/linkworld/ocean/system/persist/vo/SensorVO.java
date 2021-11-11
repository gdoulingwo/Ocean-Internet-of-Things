package org.linkworld.ocean.system.persist.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author HALOXIAO
 * @since 2021-11-10 21:36
 **/
@Data
public class SensorVO {


    private Long id;

    private String name;


    private Object config;

    private Double longitude;

    private Double latitude;

    private String note;

    private String topic;

    private Timestamp createTime;


}
