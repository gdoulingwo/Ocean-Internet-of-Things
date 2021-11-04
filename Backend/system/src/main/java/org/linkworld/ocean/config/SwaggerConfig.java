package org.linkworld.ocean.config;

/*
 *
 *@Author  liu
 *@Creat Time   2021/11/4  10:30
 *@System Data  2021 11
 *
 */

import com.sun.org.apache.bcel.internal.generic.NEW;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;



@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket getDocket(Environment environment) {
        String groupName = "海洋物联网loT";
        Profiles profiles = Profiles.of("dev");
        boolean dev = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(getApiInfo())
                .enable(dev)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.linkworld.ocean"))
                .build()
                ;

    }

    private ApiInfo getApiInfo() {
     return new ApiInfoBuilder()
                .title("Ocean-Internet-of-Things接口文档")
                .description("Ocean-loT")
                .version("v1.0")
                .build();
    }
}
