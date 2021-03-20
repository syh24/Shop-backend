package com.shop.mew.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.charset.StandardCharsets;

import static java.util.Collections.singleton;
import static springfox.documentation.builders.RequestHandlerSelectors.*;


@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    private ApiInfo swaggerInfo(){
        return new ApiInfoBuilder()
                .title("Mew API Documentation")
                .contact(new Contact("syh",null,"dbsgur6896@naver.com"))
                .version("1.0.0")
                .build();
    }

    @Bean
    public Docket api (){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerInfo())
                .produces(singleton("application/json"))
                .consumes(singleton("application/json"))
                .useDefaultResponseMessages(false)
                .select()
                .apis(withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}

