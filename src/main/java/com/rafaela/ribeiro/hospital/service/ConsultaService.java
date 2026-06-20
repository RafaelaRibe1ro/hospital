package com.rafaela.ribeiro.hospital.service;

import org.springframework.stereotype.Service;

import com.rafaela.ribeiro.hospital.dto.ConsultaRequest;
import com.rafaela.ribeiro.hospital.entity.Consulta;
import com.rafaela.ribeiro.hospital.repository.ConsultaRepository;
import com.rafaela.ribeiro.hospital.repository.MedicoRepository;
import com.rafaela.ribeiro.hospital.repository.PacienteRepository;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public Consulta cadastrar(ConsultaRequest request) {
        Consulta consulta = new Consulta();
        consulta.setPaciente(pacienteRepository.getReferenceById(request.pacienteId()));
        consulta.setMedico(medicoRepository.getReferenceById(request.medicoId()));
        consulta.setDataConsulta(request.dataConsulta());
        consulta.setObservacoes(request.observacoes());
        return consultaRepository.save(consulta);
    }
}
