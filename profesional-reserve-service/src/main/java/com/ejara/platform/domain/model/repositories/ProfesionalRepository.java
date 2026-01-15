package com.ejara.platform.domain.model.repositories;

import com.ejara.platform.domain.model.entities.Profesional;
import io.smallrye.mutiny.Uni;

import java.util.List;
import java.util.UUID;

public interface ProfesionalRepository {
    Uni<Profesional> guardar(Profesional p);
    Uni<List<Profesional>> listar();
    Uni<Profesional> buscarPorId(UUID id);
}
