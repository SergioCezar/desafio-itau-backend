package br.com.meuprojeto.desafioitau.Docs;

import br.com.meuprojeto.desafioitau.Transações.TransacaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Transações", description = "Endpoints responsáveis por criar e adicionar as transações em uma lista assim como limpá-la. ")
public interface TransacaoControllerDoc {

    @Operation(summary = "Cria novas transações." , description = "Recebe uma transação válida e adiciona em uma lista.")
    @ApiResponse(responseCode ="201", description = "Transação criada com sucesso!")
    @ApiResponse(responseCode ="422", description = "Erro de validação!")
    @ApiResponse(responseCode ="400", description = "Requisição inválida (JSON mal formatado)")
    ResponseEntity<Void> adicionar(@RequestBody TransacaoRequestDTO transacaoRequest);


    @Operation(summary = "Deleta transações." , description = "Remove todas as transações adicionadas à lista.")
    @ApiResponse(responseCode ="200", description = "Todas as transações foram deletadas com sucesso!")
    @ApiResponse(responseCode = "500", description = "Erro ao deletar transações!")
    ResponseEntity<Void> deletar();

}
