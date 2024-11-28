package com.example.doc.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(externalDocs = @ExternalDocumentation(
        description = "项目编译部署说明", url = "http://localhost:9001/readme.md"),
        servers = {@Server(description = "开发环境服务器", url = "http://localhost:9001"
        ),
                @Server(description = "测试环境服务器", url = "https://test.xiezhr.com")})
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                // 配置接口文档基本信息
                .info(this.getApiInfo());
    }

    /**
     * 配置接口文档基本信息
     */
    private Info getApiInfo() {
        return new Info()
                // 配置文档标题
                .title("项目接口文档")
                // 配置文档描述
                .description("demo测试用例")
                // 配置作者信息
                .contact(new Contact().name("三个三").email("2029364173@qq.com").url("https://www.xiezhrspace.cn"))
                // 配置License许可证信息
                .license(new License().name("MIT").url("https://www.xiezhrspace.cn"))
                // 概述信息
                .summary("测试使用").termsOfService("https://www.xiezhrspace.cn")
                // 配置版本号
                .version("1.0");
    }
}

