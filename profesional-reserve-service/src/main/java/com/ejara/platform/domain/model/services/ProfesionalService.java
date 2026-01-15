package com.ejara.platform.domain.model.services;

import com.ejara.platform.domain.model.entities.Profesional;
import io.smallrye.mutiny.Uni;

import java.util.List;
import java.util.UUID;

public interface ProfesionalService {
    Uni<Profesional> crear(Profesional profesional);

    Uni<Profesional> inactivar(UUID id);

    Uni<List<Profesional>> listar();

    Uni<Profesional> buscarPorId(UUID id);
}
