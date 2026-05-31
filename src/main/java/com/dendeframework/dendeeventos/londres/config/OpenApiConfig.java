package com.dendeframework.dendeeventos.londres.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DendeEventos API")
                        .version("1.0")
                        .description("A dende-eventos-api é uma API REST responsável por gerenciar usuários, eventos e ingressos da plataforma Dendê Eventos."));
    }
}
