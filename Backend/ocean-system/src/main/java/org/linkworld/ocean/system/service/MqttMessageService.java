package org.linkworld.ocean.system.service;

import org.linkworld.ocean.system.persist.dto.MessageHeaderDTO;
import org.springframework.stereotype.Service;

/**
 * @author HALOXIAO
 * @since 2021-11-06 23:14
 **/
@Service
public interface MqttMessageService {
    public void messageHandle(MessageHeaderDTO header, String payload);

    }
