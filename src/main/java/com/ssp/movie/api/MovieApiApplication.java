package com.ssp.movie.api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApiApplication.class, args);
    }

    @Bean
    public OpenAPI movieOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Movie Recommendations API")
                        .description("Save time choosing - get just 3 movie recommendations (with a little bit of filtering available)")
                        .version("v0.0.1")
                );
    }
}
