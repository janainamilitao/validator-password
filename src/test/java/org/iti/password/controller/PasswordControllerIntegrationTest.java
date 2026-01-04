// language: java
package org.iti.password.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PasswordControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static record Payload(String password) {}

    @Test
    void senhaValida_retornaValidTrue() throws Exception {
        var payload = new Payload("AbTp9!fok");
        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(true));
    }

    @Test
    void tamanhoMenorQue9_retornaValidFalse() throws Exception {
        var payload = new Payload("AbTp9!fo"); // 8 chars
        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false));
    }

    @Test
    void semDigito_retornaValidFalse() throws Exception {
        var payload = new Payload("AbTp!fokK");
        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false));
    }

    @Test
    void semMinuscula_retornaValidFalse() throws Exception {
        var payload = new Payload("ABTP9!FOK");
        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false));
    }

    @Test
    void semMaiuscula_retornaValidFalse() throws Exception {
        var payload = new Payload("abtp9!fok");
        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false));
    }

    @Test
    void semEspecial_retornaValidFalse() throws Exception {
        var payload = new Payload("AbTp9afok");
        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false));
    }

    @Test
    void comCaracterRepetido_retornaValidFalse() throws Exception {
        var payload = new Payload("AbTp9!foO"); // 'o' ou 'O' repetido (case-insensitive)
        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false));
    }

    @Test
    void comEspaco_retornaValidFalse() throws Exception {
        var payload = new Payload("AbTp9! foK");
        mockMvc.perform(post("/api/password/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value(false));
    }
}
