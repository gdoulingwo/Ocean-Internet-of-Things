package org.linkworld.ocean;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.linkworld.ocean.config.SwaggerConfig;
import org.linkworld.ocean.service.impl.SysRoleServiceImpl;
import org.linkworld.ocean.service.impl.SysUserServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author HALOXIAO
 * @date 2021/1/20
 */
@Api(hidden = false)
@EnableSwagger2WebMvc
@EntityScan(basePackages = "me.zhengjie")
@EnableJpaRepositories(basePackages = "me.zhengjie.**.repository")
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableConfigurationProperties
@MapperScan("org.linkworld.ocean.mapper")
@SpringBootApplication(scanBasePackages = {"me.zhengjie", "org.linkworld.ocean"})
public class Application {

    public static void main(String[] args) {
        System.setProperty("java.security.auth.login.config", "classpath:security/kafka_server_jaas.conf");
        ConfigurableApplicationContext ioc = SpringApplication.run(Application.class, args);
        SwaggerConfig bean = ioc.getBean(SwaggerConfig.class);
        System.out.println(bean);

    }
}


