package com.rafaela.ribeiro.hospital.controller;

import com.rafaela.ribeiro.hospital.dto.MedicoRankingDTO;
import com.rafaela.ribeiro.hospital.entity.Medico;
import com.rafaela.ribeiro.hospital.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Medico> cadastrar(@RequestBody @Valid Medico medico) {
        return ResponseEntity.status(201).body(service.cadastrar(medico));
    }

    @GetMapping
    public ResponseEntity<List<Medico>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<MedicoRankingDTO>> ranking() {
        return ResponseEntity.ok(service.rankingPorConsultas());
    }
}
