package br.com.meuprojeto.desafioitau;

import br.com.meuprojeto.desafioitau.Estatisticas.EstatisticasProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({EstatisticasProperties.class})
@SpringBootApplication
public class DesafioItauApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioItauApplication.class, args);
    }

}
