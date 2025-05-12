package com.github.nberezhnykh.usersubscriptionservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация Swagger/OpenAPI для документирования API.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Создает и настраивает объект OpenAPI с основной информацией об API.
     *
     * @return сконфигурированный объект OpenAPI.
     */
    @Bean
    public OpenAPI usersSubscriptionsServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Users and Subscriptions API")
                        .description("API for managing users and their subscriptions")
                        .version("v1"));
    }
}
