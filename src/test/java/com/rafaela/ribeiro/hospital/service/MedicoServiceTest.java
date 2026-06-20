package com.rafaela.ribeiro.hospital.service;

import com.rafaela.ribeiro.hospital.entity.Medico;
import com.rafaela.ribeiro.hospital.repository.MedicoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicoServiceTest {

    @Mock
    private MedicoRepository repository;

    @InjectMocks
    private MedicoService service;

    @Test
    void deveCadastrarMedico() {
        Medico medico = novoMedico();
        when(repository.save(medico)).thenReturn(medico);

        Medico resultado = service.cadastrar(medico);

        assertThat(resultado).isEqualTo(medico);
        verify(repository).save(medico);
    }

    @Test
    void deveListarMedicos() {
        List<Medico> medicos = List.of(novoMedico());
        when(repository.findAll()).thenReturn(medicos);

        List<Medico> resultado = service.listar();

        assertThat(resultado).hasSize(1);
        verify(repository).findAll();
    }

    private Medico novoMedico() {
        Medico m = new Medico();
        m.setNome("Dr. Teste");
        m.setCrm("CRM-00000");
        m.setEspecialidade("Clínico Geral");
        return m;
    }
}
