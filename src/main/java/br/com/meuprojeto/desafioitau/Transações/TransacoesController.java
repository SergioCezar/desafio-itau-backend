package br.com.meuprojeto.desafioitau.Transações;

import br.com.meuprojeto.desafioitau.Docs.EstatisticaControllerDoc;
import br.com.meuprojeto.desafioitau.Docs.TransacaoControllerDoc;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transacao")
public class TransacoesController implements TransacaoControllerDoc {

    private final TransacaoService transacaoService;

    public TransacoesController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Void> adicionar(@RequestBody TransacaoRequestDTO transacaoRequest){

        log.info("Recebendo nova transação: {}", transacaoRequest);

        try {
            transacaoService.processarTransacao(transacaoRequest);
            log.debug("Validação da transação OK");

            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (IllegalArgumentException e) {
            log.warn("Erro de validação na transação: {} | Motivo: {}",
                    transacaoRequest, e.getMessage());

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();

        } catch (Exception e) {
            log.error("Erro inesperado ao processar transação: {}",
                    transacaoRequest, e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(){

        log.info("Recebida requisição para deletar todas as transações");

        try {
            transacaoService.deletarTodas();
            log.info("Todas as transações foram deletadas com sucesso");

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception e) {
            log.error("Erro ao deletar transações", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}