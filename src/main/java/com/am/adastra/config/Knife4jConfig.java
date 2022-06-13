package com.am.adastra.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {

    /**
     * 【重要】指定Controller包路径
     */
    private String basePackage = "com.am.adastra.controller";
    /**
     * 分组名称
     */
    private String groupName = "product";
    /**
     * 主机名
     */
    private String host = "http://adastra.isamumu.cn";
    /**
     * 标题
     */
    private String title = "adastra 视频网站";
    /**
     * 简介
     */
    private String description = "adastra在线API文档";
    /**
     * 服务条款URL
     */
    private String termsOfServiceUrl = "http://www.apache.org/licenses/LICENSE-2.0";
    /**
     * 联系人
     */
    private String contactName = "adastra 开发组";
    /**
     * 联系网址
     */
    private String contactUrl = "http://adastra.isamumu.cn";
    /**
     * 联系邮箱
     */
    private String contactEmail = "arturiamumu@gmail.com";
    /**
     * 版本号
     */
    private String version = "1.0.0";

    @Autowired(required = false)
    private OpenApiExtensionResolver openApiExtensionResolver;

    @Bean
    public Docket docket() {
        String groupName = "1.0.0";
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .host(host)
                .apiInfo(apiInfo())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions(groupName));
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .version(version)
                .build();
    }
}
