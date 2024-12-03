package com.wnc.filmserver.config;

import com.wnc.filmserver.constant.OpenApiConstant;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        servers = {
                @Server(url = OpenApiConstant.DEV_SERVER_URL, description = OpenApiConstant.DEV_SERVER_DESCRIPTION)
        }
)
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title(OpenApiConstant.TITLE).version(OpenApiConstant.VERSION).description(OpenApiConstant.DESCRIPTION));
    }
}
