package br.com.meuprojeto.desafioitau.Estatisticas;

import br.com.meuprojeto.desafioitau.Docs.EstatisticaControllerDoc;
import br.com.meuprojeto.desafioitau.Transações.TransacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@Slf4j
@RestController
@RequestMapping("/estatistica")
public class EstatisticasController implements EstatisticaControllerDoc {

    private final EstatisticasProperties estatisticasProperties;
    private final TransacaoRepository transacaoRepository;

    public EstatisticasController(EstatisticasProperties estatisticasProperties,
                                  TransacaoRepository transacaoRepository) {
        this.estatisticasProperties = estatisticasProperties;
        this.transacaoRepository = transacaoRepository;
    }

    @GetMapping
    public ResponseEntity<EstatisticaDTO> estatistica() {

        log.info("Calculando estatísticas de transações nos últimos {} segundos",
                estatisticasProperties.segundos());

        try {
            final var horaInicial = OffsetDateTime.now()
                    .minusSeconds(estatisticasProperties.segundos());

            var resultado = transacaoRepository.estatistica(horaInicial);

            log.info("Estatísticas calculadas com sucesso");
            return ResponseEntity.ok(resultado);

        } catch (Exception e) {
            log.error("Erro ao calcular estatísticas", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}