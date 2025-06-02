package br.com.fiap.backendjava.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void deveCriarUsuarioComSucessoViaFormulario() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/usuarios/criar")
                        .param("nome", "Patricia")
                        .param("username", "paty@email.com")
                        .param("senha", "123456")
                        .param("role", "PROFISSIONALSAUDE")
                        .param("telefone", "11999999999")
                        .param("dataNascimento", "2000-01-01")
                        .param("documento", "12345678901")
                        .param("status", "true")

                        .param("idEndereco.rua", "Rua Teste")
                        .param("idEndereco.numero", "123")
                        .param("idEndereco.bairro", "Bairro Teste")
                        .param("idEndereco.cidade", "Cidade Teste")
                        .param("idEndereco.estado", "SP")
                        .param("idEndereco.cep", "00000-000")
                        .param("idEndereco.complemento", "Apto 101")
                        .with(csrf()))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "user", roles = {"USUARIO"})
    void deveRetornarForbiddenQuandoNaoAdminTentaCriarAdminViaFormulario() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/usuarios/criar")
                        .param("nome", "Admin Proibido")
                        .param("username", "forbiddenadmin@email.com")
                        .param("senha", "123")
                        .param("role", "ADMIN")
                        .param("telefone", "11999999999")
                        .param("dataNascimento", "2000-01-01")
                        .param("documento", "12345678901")
                        .param("status", "true")
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(username = "user", roles = {"PROFISSIONALSAUDE"})
    void deveRetornarForbiddenQuandoNaoAdminTentaCriarAdmin() throws Exception {
        String json = """
                {
                    "nome": "Admin Proibido",
                    "username": "forbiddenadmin@email.com",
                    "senha": "123",
                    "role": "ADMIN",
                    "telefone": "11999999999",
                    "dataNascimento": "2000-01-01",
                    "documento": "12345678901",
                    "status": true,
                    "idEndereco": {
                        "rua": "Rua Teste",
                        "numero": "123",
                        "bairro": "Bairro Teste",
                        "cidade": "Cidade Teste",
                        "estado": "SP",
                        "cep": "00000-000",
                        "complemento": "Apto 101"
                    }
                }
                """;

        mockMvc.perform(post("/usuarios/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }
}