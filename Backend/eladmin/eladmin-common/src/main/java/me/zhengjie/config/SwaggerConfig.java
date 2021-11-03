/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.zhengjie.config;

import com.fasterxml.classmate.TypeResolver;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRuleConvention;
import springfox.documentation.schema.TypeNameExtractor;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.awt.print.Pageable;
import java.util.Collections;
import java.util.List;

import static cn.hutool.core.collection.CollUtil.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * api页面 /doc.html
 * @author Zheng Jie
 * @date 2018-11-23
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SwaggerConfig {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.token-start-with}")
    private String tokenStartWith;

    @Value("${swagger.enabled:false}")
    private Boolean enabled;

    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Bean
    public Docket createRestApi_1() {
        String groupName = "渔业iot平台";
        // 将两个包分组存放
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enabled)
                .apiInfo(apiInfo())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.linkworld.fishing.system"))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions(groupName))
                .securityContexts(securityContext())
                .securitySchemes(securitySchemes());
    }

    @Bean
    public Docket createRestApi_2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enabled)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("me.zhengjie"))
                .paths(PathSelectors.any())
                .build()
                .groupName("el-admin管理系统")
                .securityContexts(securityContext())
                .securitySchemes(securitySchemes());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .version("BETA")
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(new ApiKey("JWT", "", "header"));
    }

    private List<SecurityContext> securityContext() {
        SecurityContext securityContext = SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
        return Collections.singletonList(securityContext);
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
    }
}
