package com.ejara.platform.domain.model.repositories;

import com.ejara.platform.domain.model.entities.Reserva;
import io.smallrye.mutiny.Uni;

import java.util.List;
import java.util.UUID;

public interface ReservaRepository {
    Uni<Boolean> existeSolapamiento(Reserva r);
    Uni<Reserva> guardar(Reserva r);
    Uni<Reserva> buscarPorId(UUID id);
    Uni<List<Reserva>> reservasActivas();
}
