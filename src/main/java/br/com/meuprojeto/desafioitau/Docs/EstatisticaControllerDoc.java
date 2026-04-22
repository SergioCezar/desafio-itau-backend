package br.com.meuprojeto.desafioitau.Docs;

import br.com.meuprojeto.desafioitau.Estatisticas.EstatisticaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "Estatísticas",
        description = "Endpoint responsável por calcular estatísticas das transações com base em um intervalo de tempo."
)
public interface EstatisticaControllerDoc {

    @Operation(
            summary = "Retorna estatísticas das transações",
            description = "Calcula e retorna estatísticas (como soma, média, mínimo, máximo e quantidade) das transações realizadas nos últimos 60 segundos (configurável)."
    )

    @ApiResponse(responseCode = "200", description = "Estatísticas calculadas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")

    ResponseEntity<EstatisticaDTO> estatistica();
}