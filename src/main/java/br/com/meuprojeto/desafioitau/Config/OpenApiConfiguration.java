package br.com.meuprojeto.desafioitau.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI apiInfo() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Desafio ITAÚ - API de Transações")
                                .description("API REST para recebimento de transações e cálculo de estatísticas em tempo real (últimos 60 segundos).")
                                .version("1.0.0")
                );
    }
}
