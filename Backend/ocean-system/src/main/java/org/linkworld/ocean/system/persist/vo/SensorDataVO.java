package org.linkworld.ocean.system.persist.vo;

import lombok.Data;

/**
 * @author HALOXIAO
 * @since 2021-11-08 10:30
 **/
@Data
public class SensorDataVO {

    private String data;
    private String mean;
    private String topic;
    private Double latitude;
    private Double longitude;

}
