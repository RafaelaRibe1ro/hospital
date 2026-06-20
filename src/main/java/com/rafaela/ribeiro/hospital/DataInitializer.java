package com.rafaela.ribeiro.hospital;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rafaela.ribeiro.hospital.entity.Medico;
import com.rafaela.ribeiro.hospital.entity.Paciente;
import com.rafaela.ribeiro.hospital.repository.MedicoRepository;
import com.rafaela.ribeiro.hospital.repository.PacienteRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public DataInitializer(MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public void run(String... args) {
        if (medicoRepository.count() == 0) {
            Medico cardiologista = new Medico();
            cardiologista.setNome("Dr. Caio Luiz");
            cardiologista.setCrm("CRM-54321");
            cardiologista.setEspecialidade("Cardiologia");

            Medico ortopedista = new Medico();
            ortopedista.setNome("Dra. Ana Luiza");
            ortopedista.setCrm("CRM-67890");
            ortopedista.setEspecialidade("Ortopedia");

            medicoRepository.saveAll(List.of(cardiologista, ortopedista));
        }

        if (pacienteRepository.count() == 0) {
            Paciente joao = new Paciente();
            joao.setNome("Yan Santos");
            joao.setCpf("111.111.111-11");
            joao.setDataNascimento(LocalDate.of(2000, 5, 25));
            joao.setTelefone("(31) 99899-2001");

            Paciente maria = new Paciente();
            maria.setNome("Rafaela Oliveira");
            maria.setCpf("222.222.222-22");
            maria.setDataNascimento(LocalDate.of(2002, 1, 7));
            maria.setTelefone("(31) 98888-0102");

            pacienteRepository.saveAll(List.of(joao, maria));
        }
    }
}
