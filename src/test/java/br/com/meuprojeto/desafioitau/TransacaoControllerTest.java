package br.com.meuprojeto.desafioitau;

import br.com.meuprojeto.desafioitau.Transações.TransacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TransacaoService transacaoService;

    @BeforeEach
    void limpar() {
        transacaoService.deletarTodas();
    }

    private String criarJson(double valor, OffsetDateTime dataHora) throws Exception {
        return objectMapper.writeValueAsString(
                        new Object() {
                            public final double valorField = valor;
                            public final OffsetDateTime dataHoraField = dataHora;
                        }
                ).replace("valorField", "valor")
                .replace("dataHoraField", "dataHora");
    }

    @Test
    void deveCriarTransacaoValida() throws Exception {
        String json = """
        {
            "valor": 100.0,
            "dataHora": "%s"
        }
        """.formatted(OffsetDateTime.now());

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void deveRetornar422ParaValorNegativo() throws Exception {
        String json = """
        {
            "valor": -10.0,
            "dataHora": "%s"
        }
        """.formatted(OffsetDateTime.now());

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void deveRetornar422ParaDataFutura() throws Exception {
        String json = """
        {
            "valor": 10.0,
            "dataHora": "%s"
        }
        """.formatted(OffsetDateTime.now().plusSeconds(60));

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void deveRetornar400ParaJsonInvalido() throws Exception {
        String json = "{ valor: }";

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveDeletarTransacoes() throws Exception {
        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());
    }
}