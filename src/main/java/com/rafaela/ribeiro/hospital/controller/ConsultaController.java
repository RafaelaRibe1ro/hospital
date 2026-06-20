package com.rafaela.ribeiro.hospital.controller;

import com.rafaela.ribeiro.hospital.dto.ConsultaRequest;
import com.rafaela.ribeiro.hospital.entity.Consulta;
import com.rafaela.ribeiro.hospital.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Consulta> cadastrar(@RequestBody @Valid ConsultaRequest request) {
        return ResponseEntity.status(201).body(service.cadastrar(request));
    }
}
