package br.com.meuprojeto.desafioitau.Transações;

import br.com.meuprojeto.desafioitau.Estatisticas.EstatisticaDTO;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class TransacaoRepository {

    private final List<TransacaoRequestDTO> listaDeTransacoes = new CopyOnWriteArrayList<>();

    public void salvarDados(TransacaoRequestDTO transacaoRequestDTO) {
        listaDeTransacoes.add(transacaoRequestDTO);
    }

    public void deletarDados() {
        listaDeTransacoes.clear();
    }

    public EstatisticaDTO estatistica(OffsetDateTime horaInicial) {

        listaDeTransacoes.removeIf(t ->
                t.getDataHora().isBefore(horaInicial)
        );

        if (listaDeTransacoes.isEmpty()) {
            return new EstatisticaDTO(0, 0, 0, 0, 0);
        }

        var summary = listaDeTransacoes.stream()
                .mapToDouble(t -> t.getValor().doubleValue())
                .summaryStatistics();

        return new EstatisticaDTO(
                summary.getCount(),
                summary.getSum(),
                summary.getAverage(),
                summary.getMin(),
                summary.getMax()
        );
    }
}