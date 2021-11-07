package org.linkworld.ocean.system.persist.dto;

import lombok.Data;

/**
 * @author HALOXIAO
 * @since 2021-11-07 15:12
 **/
@Data
public class MessageHeaderDTO {

    private String topic;
    private Integer qos;
    private Boolean duplicate;
    private Boolean receivedRetained;
    private Long timestamp;

}
