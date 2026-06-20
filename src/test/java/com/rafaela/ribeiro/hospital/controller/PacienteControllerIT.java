package com.rafaela.ribeiro.hospital.controller;

import com.rafaela.ribeiro.hospital.TestcontainersConfiguration;
import com.rafaela.ribeiro.hospital.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestcontainersConfiguration.class)
class PacienteControllerIT {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PacienteRepository pacienteRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
        pacienteRepository.deleteAll();
    }

    @Test
    void deveCadastrarPaciente() throws Exception {
        mockMvc.perform(post("/pacientes")
                        .with(httpBasic("admin", "admin123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPaciente()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Ana Costa"))
                .andExpect(jsonPath("$.cpf").value("333.333.333-33"));
    }

    @Test
    void deveBuscarPacientePorId() throws Exception {
        Long id = cadastrarERetornarId();

        mockMvc.perform(get("/pacientes/{id}", id)
                        .with(httpBasic("admin", "admin123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value("Ana Costa"));
    }

    @Test
    void deveListarPacientes() throws Exception {
        cadastrarERetornarId();

        mockMvc.perform(get("/pacientes")
                        .with(httpBasic("admin", "admin123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].nome").value("Ana Costa"));
    }

    @Test
    void deveExcluirPaciente() throws Exception {
        Long id = cadastrarERetornarId();

        mockMvc.perform(delete("/pacientes/{id}", id)
                        .with(httpBasic("admin", "admin123")))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/pacientes/{id}", id)
                        .with(httpBasic("admin", "admin123")))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRetornar401SemAutenticacao() throws Exception {
        mockMvc.perform(get("/pacientes"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void devePermitirActuatorHealthSemAutenticacao() throws Exception {
        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk());
    }

    private Long cadastrarERetornarId() throws Exception {
        mockMvc.perform(post("/pacientes")
                        .with(httpBasic("admin", "admin123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPaciente()))
                .andExpect(status().isCreated());

        return pacienteRepository.findAll().get(0).getId();
    }

    private String jsonPaciente() {
        return """
                {
                    "nome": "Ana Costa",
                    "cpf": "333.333.333-33",
                    "dataNascimento": "2001-02-15",
                    "telefone": "(11) 99121-2222"
                }
                """;
    }
}
