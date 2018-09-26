/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwt.jwtsecurity.swagger;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author root
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    
   ApiInfo apiInfo() {
   return new ApiInfoBuilder()
       .title("Bridge Pay API")
       .description("API Reference")
       .version("1.0.0")
       .build();
 }


 @Bean
 public Docket productApi() {
     return new Docket(DocumentationType.SWAGGER_2)
             .apiInfo(apiInfo())
             .securitySchemes(Arrays.asList(new ApiKey[]{new ApiKey("Authorization", "XAuth", "header")}))
             .select().paths(PathSelectors.any()).apis(RequestHandlerSelectors.basePackage("com.jwt.jwtsecurity.controller")).build()
            .pathMapping("/");
 }

}