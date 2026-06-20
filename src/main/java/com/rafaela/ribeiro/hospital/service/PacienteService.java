package com.rafaela.ribeiro.hospital.service;

import com.rafaela.ribeiro.hospital.entity.Paciente;
import com.rafaela.ribeiro.hospital.repository.PacienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public Paciente cadastrar(Paciente paciente) {
        return repository.save(paciente);
    }

    public Paciente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Paciente> listar() {
        return repository.findAll();
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}
