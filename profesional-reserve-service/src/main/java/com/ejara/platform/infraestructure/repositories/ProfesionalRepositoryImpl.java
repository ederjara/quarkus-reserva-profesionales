package com.ejara.platform.infraestructure.repositories;

import com.ejara.platform.domain.model.entities.Profesional;
import com.ejara.platform.domain.model.repositories.ProfesionalRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProfesionalRepositoryImpl implements ProfesionalRepository {

    @Override
    public Uni<Profesional> guardar(Profesional p) {
        return p.persist();
    }

    @Override
    public Uni<List<Profesional>> listar() {
        return Profesional.listAll();
    }

    @Override
    public Uni<Profesional> buscarPorId(UUID id) {
        return Profesional.findById(id);
    }
}
