package com.ejara.platform.interfaces.resources.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ReservaDetalleRankingResponse(
        UUID id,
        String cliente,
        String horaInicio,
        String horaFin
) {
}