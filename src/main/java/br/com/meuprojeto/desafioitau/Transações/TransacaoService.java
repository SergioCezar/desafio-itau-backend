package br.com.meuprojeto.desafioitau.Transações;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void validarTransacao(TransacaoRequestDTO transacaoRequestDTO) {

        if (transacaoRequestDTO == null) {
            throw new IllegalArgumentException("Erro: Corpo da requisição não pode ser nulo");
        }

        if (transacaoRequestDTO.getValor() == null) {
            throw new IllegalArgumentException("Erro: Valor não informado");
        }

        if (transacaoRequestDTO.getDataHora() == null) {
            throw new IllegalArgumentException("Erro: Data/hora não informada");
        }

        if (transacaoRequestDTO.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Erro: Transação deve ter valor maior ou igual a zero");
        }

        if (transacaoRequestDTO.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Erro: Data da transação não pode estar no futuro");
        }
    }

    public void processarTransacao(TransacaoRequestDTO dto) {
        validarTransacao(dto);
        transacaoRepository.salvarDados(dto);
    }

    public void deletarTodas() {
        transacaoRepository.deletarDados();
    }
}
