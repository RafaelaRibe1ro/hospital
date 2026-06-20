package com.rafaela.ribeiro.hospital.service;

import com.rafaela.ribeiro.hospital.dto.MedicoRankingDTO;
import com.rafaela.ribeiro.hospital.entity.Medico;
import com.rafaela.ribeiro.hospital.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public Medico cadastrar(Medico medico) {
        return repository.save(medico);
    }

    public List<Medico> listar() {
        return repository.findAll();
    }

    public List<MedicoRankingDTO> rankingPorConsultas() {
        return repository.findMedicosOrdenadosPorTotalDeConsultas();
    }
}
