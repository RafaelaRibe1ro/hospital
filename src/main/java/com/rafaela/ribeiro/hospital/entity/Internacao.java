package com.rafaela.ribeiro.hospital.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor
@Entity
public class Internacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataEntrada;

    private LocalDate dataAlta;

    private String quarto;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
