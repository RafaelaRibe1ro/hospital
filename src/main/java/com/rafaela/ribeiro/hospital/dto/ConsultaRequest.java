package com.rafaela.ribeiro.hospital.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaRequest(
        @NotNull Long pacienteId,
        @NotNull Long medicoId,
        LocalDateTime dataConsulta,
        String observacoes
) {
}
