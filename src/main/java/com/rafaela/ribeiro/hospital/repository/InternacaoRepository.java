package com.rafaela.ribeiro.hospital.repository;

import com.rafaela.ribeiro.hospital.entity.Internacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternacaoRepository extends JpaRepository<Internacao, Long> {
}
