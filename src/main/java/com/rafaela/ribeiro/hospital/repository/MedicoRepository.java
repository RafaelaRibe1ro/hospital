package com.rafaela.ribeiro.hospital.repository;

import com.rafaela.ribeiro.hospital.entity.Medico;
import com.rafaela.ribeiro.hospital.dto.MedicoRankingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("""
            SELECT new com.rafaela.ribeiro.hospital.dto.MedicoRankingDTO(
                m.id, m.nome, m.crm, m.especialidade, COUNT(c))
            FROM Medico m LEFT JOIN m.consultas c
            GROUP BY m.id, m.nome, m.crm, m.especialidade
            ORDER BY COUNT(c) DESC
            """)
    List<MedicoRankingDTO> findMedicosOrdenadosPorTotalDeConsultas();
}
