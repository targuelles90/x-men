package com.meli.xmen.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocsConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("X-men Project")
                .description("Api that allows to register human Dna to know if they are mutants or not. " +
                        "It also provides some statistics, to know the ratio between humans and mutants.");
        return new OpenAPI().info(info);
    }
}
