package com.yang.erp.config.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * <p>
 * Swagger 配置类
 * 原生： /swagger-ui.html
 * 美化：/doc.html
 * </p>
 *
 * @author 曹申阳
 * @since 2022-11-14 17:34:41
 */
@Configuration
@EnableOpenApi
@EnableKnife4j
public class SwaggerConfig {

    @Value("${swagger.enable:false}")
    public Boolean enable;


    @Bean
    public Docket createRestApi() {
        // 建造者模式构建Docket
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .groupName("ERP")
                .select()
                // 需要放出的接口
                .apis(RequestHandlerSelectors.basePackage("com.yang.erp"))
                // 过滤
                .paths(PathSelectors.any())
                .build()
                .enable(enable);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ERP 接口文档")
                .description("描述")
                .contact(new Contact("csy", "xxx.com", "2417254000@qq.com"))
                .version("1.0")
                .build();
    }

}
