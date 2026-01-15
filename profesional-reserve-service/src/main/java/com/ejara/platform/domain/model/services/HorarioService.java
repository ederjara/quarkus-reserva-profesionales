package com.ejara.platform.domain.model.services;

import com.ejara.platform.domain.model.entities.Horario;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface HorarioService {
    Uni<Horario> registrar(Horario horario);
    Uni<List<Horario>> listar();
}
