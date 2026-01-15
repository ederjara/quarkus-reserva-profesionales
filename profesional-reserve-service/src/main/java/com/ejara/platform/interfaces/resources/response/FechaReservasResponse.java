package com.ejara.platform.interfaces.resources.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record FechaReservasResponse(
        LocalDate fecha,
        List<ReservaDetalleRankingResponse> reservas
) {
}
