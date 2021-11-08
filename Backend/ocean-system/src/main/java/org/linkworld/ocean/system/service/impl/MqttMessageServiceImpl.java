package org.linkworld.ocean.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.zhengjie.utils.SecurityUtils;
import org.linkworld.ocean.system.persist.dto.MessageHeaderDTO;
import org.linkworld.ocean.system.persist.module.OceanSensorData;
import org.linkworld.ocean.system.service.MqttMessageService;
import org.linkworld.ocean.system.service.OceanSensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author HALOXIAO
 * @since 2021-11-06 23:26
 **/
@Service
public class MqttMessageServiceImpl implements MqttMessageService {


    @Autowired
    private OceanSensorDataService oceanSensorDataService;


    /**
     * 后续添加缓存，Batch方式写入DB
     * 将数据存入clickhouse or mysql
     */
    public void messageHandle(MessageHeaderDTO header, String payload) {
        JSONObject jsonObject = JSON.parseObject(payload);
        OceanSensorData data = new OceanSensorData();
        data.setData(payload);
        data.setUserId(SecurityUtils.getCurrentUserId());
        data.setTopic(header.getTopic());
        data.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        data.setConfigId(jsonObject.getObject("config_id", Integer.class));
        oceanSensorDataService.save(data);
    }


}
