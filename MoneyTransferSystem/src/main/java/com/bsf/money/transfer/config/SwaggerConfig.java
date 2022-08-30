package com.bsf.money.transfer.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Money Transfer API Documentation")
                .description("Money Transfer API Documentation")
                .build();
    }
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bsf.money.transfer"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo());
    }
}
