package com.kshrd.derphsar_api.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DerPhsar Project API")
                .description("Korea Software HRD Center \uD83D\uDC95")
                .termsOfServiceUrl("https://spring.io/")
                .license("License of API")
                .licenseUrl("Email")
                .version("HRD 8th Generation")
                .contact(new Contact("Copyright by DerPhsar team", "https://spring.io/", "hpc@gmail.com"))
                .build();
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build().apiInfo(apiInfo());
    }

}
