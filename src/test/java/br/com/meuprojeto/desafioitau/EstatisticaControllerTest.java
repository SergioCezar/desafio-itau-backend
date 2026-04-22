package br.com.meuprojeto.desafioitau;

import br.com.meuprojeto.desafioitau.Transações.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EstatisticaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransacaoService transacaoService;

    @BeforeEach
    void limpar() {
        transacaoService.deletarTodas();
    }

    @Test
    void deveRetornarEstatisticasZeradasQuandoNaoHaTransacoes() throws Exception {

        mockMvc.perform(get("/estatistica"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(0))
                .andExpect(jsonPath("$.sum").value(0.0))
                .andExpect(jsonPath("$.avg").value(0.0))
                .andExpect(jsonPath("$.min").value(0.0))
                .andExpect(jsonPath("$.max").value(0.0));
    }

    @Test
    void deveCalcularEstatisticasCorretamente() throws Exception {

        String json1 = """
        {
            "valor": 10.0,
            "dataHora": "%s"
        }
        """.formatted(OffsetDateTime.now());

        String json2 = """
        {
            "valor": 20.0,
            "dataHora": "%s"
        }
        """.formatted(OffsetDateTime.now());

        mockMvc.perform(post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json1));

        mockMvc.perform(post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json2));

        mockMvc.perform(get("/estatistica"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(2))
                .andExpect(jsonPath("$.sum").value(30.0))
                .andExpect(jsonPath("$.avg").value(15.0))
                .andExpect(jsonPath("$.min").value(10.0))
                .andExpect(jsonPath("$.max").value(20.0));
    }

    @Test
    void deveIgnorarTransacoesAntigas() throws Exception {

        String jsonAntigo = """
        {
            "valor": 50.0,
            "dataHora": "%s"
        }
        """.formatted(OffsetDateTime.now().minusSeconds(120));

        mockMvc.perform(post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAntigo));

        mockMvc.perform(get("/estatistica"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(0));
    }
}