package me.zhengjie.modules.security.security;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.time.Instant;
import java.util.Collections;

@Slf4j
@Component
public class RSAKeyManage implements CommandLineRunner {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String PUBLIC_KEY_NAME =
            RSAKeyManage.class.getName() + ":public_key";
    private static final String PRIVATE_KEY_NAME =
            RSAKeyManage.class.getName() + ":private_key";
    private static final String VERSION_NAME =
            RSAKeyManage.class.getName() + ":version";
    private static RSAKeyManage INSTANCE;

    private long version;
    private final RedisScript<String> cas;

    public RSAKeyManage() {
        if (RSAKeyManage.INSTANCE == null) {
            synchronized (RSAKeyManage.class) {
                if (RSAKeyManage.INSTANCE == null) {
                    String script = "local val = redis.call('get',KEYS[1])\n" +
                            "if val == ARGV[1] or val == nil\n" +
                            "then \n" +
                            "    redis.call('set',KEYS[1],ARGV[2])\n" +
                            "    return ARGV[2]\n" +
                            "else \n" +
                            "    return val";
                    cas = new DefaultRedisScript<>(script, String.class);
                    RSAKeyManage.INSTANCE = this;
                    return;
                }
            }
        }
        throw new IllegalStateException("repeated bean instance");
    }

    public static String getPrivateKey() {
        return INSTANCE.redisTemplate.boundValueOps(PRIVATE_KEY_NAME).get();
    }

    public static String getPublicKey() {
        return INSTANCE.redisTemplate.boundValueOps(PUBLIC_KEY_NAME).get();
    }

    public static void updateKey() {
        updateKey(false);
    }

    public static void updateKey(boolean safe) {
        INSTANCE.refresh(safe);
        log.info("密钥对已经被刷新");
    }

    private void refresh(boolean safe) {
        if (safe) {
            long newVer = version+1;
            String success = redisTemplate.execute(cas, Collections.singletonList(VERSION_NAME), version, newVer);
            if (success != null && Long.parseLong(success) >= newVer) {
                version = Long.parseLong(success);
                return;
            }
        }
        KeyPair keyPair = SecureUtil.generateKeyPair("RSA");
        this.redisTemplate.boundValueOps(PRIVATE_KEY_NAME)
                .set(Base64.encode(keyPair.getPrivate().getEncoded()));
        this.redisTemplate.boundValueOps(PUBLIC_KEY_NAME)
                .set(Base64.encode(keyPair.getPublic().getEncoded()));

        // 版本号更新
        version++;
        this.redisTemplate.opsForValue().set(VERSION_NAME, String.valueOf(version));
    }

    @Override
    public void run(String... args) {
        String ver = RSAKeyManage.INSTANCE.redisTemplate.opsForValue().get(VERSION_NAME);
        if (ver == null) {
            RSAKeyManage.updateKey();
        } else {
            version = Long.parseLong(ver);
        }
    }
}
