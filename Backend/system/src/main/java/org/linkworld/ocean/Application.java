package org.linkworld.ocean;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author HALOXIAO
 * @date 2021/1/20
 */
@Api(hidden = false)
@EnableSwagger2WebMvc
@EntityScan(basePackages = "me.zhengjie")
@SpringBootApplication(scanBasePackages = {"me.zhengjie", "org.linkworld.ocean"})
public class Application {

    public static void main(String[] args) {
        System.setProperty("java.security.auth.login.config", "classpath:security/kafka_server_jaas.conf");

        ConfigurableApplicationContext ioc = SpringApplication.run(Application.class, args);
    }
}
