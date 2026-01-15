package com.ejara.platform.interfaces.resources.response;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ProfesionalRankingResponse(
        UUID profesionalId,
        String profesionalNombre,
        Long reservasActivas,
        List<FechaReservasResponse> fechas
) {
}
