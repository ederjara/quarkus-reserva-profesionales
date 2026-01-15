package com.ejara.platform.domain.model.services;

import com.ejara.platform.domain.model.entities.Profesional;
import com.ejara.platform.domain.model.entities.Reserva;
import com.ejara.platform.interfaces.resources.response.ProfesionalRankingResponse;
import io.smallrye.mutiny.Uni;

import java.util.List;
import java.util.UUID;

public interface ReservaService {
    Uni<Reserva> crear(Reserva reserva);

    Uni<Void> cancelar(UUID id);

    Uni<List<ProfesionalRankingResponse>> listarProfesionalesPorReservasActivas();
}
