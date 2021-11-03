package me.zhengjie.modules.security.security;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RSARefreshJob {

    /**
     * 每日凌晨一点进行rsa密钥对的更换
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void updateRSATask() {
        RSAKeyManage.updateKey(true);
    }
}
