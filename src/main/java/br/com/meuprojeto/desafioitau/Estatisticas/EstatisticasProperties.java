package br.com.meuprojeto.desafioitau.Estatisticas;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "estatistica")
public record EstatisticasProperties(@NotNull @Positive Integer segundos) {

}
