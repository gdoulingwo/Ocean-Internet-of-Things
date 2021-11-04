package org.linkworld.ocean;

import io.swagger.annotations.Api;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author HALOXIAO
 * @date 2021/1/20
 */
@EnableAsync
@Api(hidden = false)
@EnableSwagger2WebMvc
@EnableScheduling
@EnableTransactionManagement
@EnableConfigurationProperties
@EntityScan(basePackages = "me.zhengjie")
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@MapperScan({"org.linkworld.ocean.persist.*.mapper","me.zhengjie"})
@EnableJpaRepositories(basePackages = "me.zhengjie.**.repository")
@SpringBootApplication(scanBasePackages = {"me.zhengjie", "org.linkworld.ocean"})
public class Application {

    public static void main(String[] args) {
        System.setProperty("java.security.auth.login.config", "classpath:security/kafka_server_jaas.conf");

        ConfigurableApplicationContext ioc = SpringApplication.run(Application.class, args);
    }
}


