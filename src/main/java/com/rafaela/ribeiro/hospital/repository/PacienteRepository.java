package com.rafaela.ribeiro.hospital.repository;

import com.rafaela.ribeiro.hospital.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
