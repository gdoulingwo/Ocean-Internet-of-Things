package org.linkworld.ocean.system.persist.vo;

import lombok.Data;

/**
 * @author HALOXIAO
 * @since 2021-11-09 15:58
 **/
@Data
public class SensorCoordinateVO {


    private Long id;
    private String name;
    private Double longitude;
    private Double latitude;

}
