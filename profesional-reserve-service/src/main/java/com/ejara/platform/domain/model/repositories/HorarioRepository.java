package com.ejara.platform.domain.model.repositories;

import com.ejara.platform.domain.model.entities.Horario;
import com.ejara.platform.domain.model.entities.Reserva;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface HorarioRepository {
    Uni<Boolean> existeSolapamiento(Horario h);
    Uni<Boolean> cubreHorario(Reserva r);
    Uni<Horario> guardar(Horario h);
    Uni<List<Horario>> listar();
}
