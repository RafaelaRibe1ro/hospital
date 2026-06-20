package com.rafaela.ribeiro.hospital.service;

import com.rafaela.ribeiro.hospital.entity.Paciente;
import com.rafaela.ribeiro.hospital.repository.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    private PacienteRepository repository;

    @InjectMocks
    private PacienteService service;

    @Test
    void deveCadastrarPaciente() {
        Paciente paciente = novoPaciente();
        when(repository.save(paciente)).thenReturn(paciente);

        Paciente resultado = service.cadastrar(paciente);

        assertThat(resultado).isEqualTo(paciente);
        verify(repository).save(paciente);
    }

    @Test
    void deveBuscarPacientePorId() {
        Paciente paciente = novoPaciente();
        when(repository.findById(1L)).thenReturn(Optional.of(paciente));

        Paciente resultado = service.buscarPorId(1L);

        assertThat(resultado).isEqualTo(paciente);
        verify(repository).findById(1L);
    }

    @Test
    void deveRemoverPaciente() {
        service.remover(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoEncontrado() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(99L))
                .isInstanceOf(ResponseStatusException.class);
    }

    private Paciente novoPaciente() {
        Paciente p = new Paciente();
        p.setNome("Teste Silva");
        p.setCpf("000.000.000-00");
        return p;
    }
}
