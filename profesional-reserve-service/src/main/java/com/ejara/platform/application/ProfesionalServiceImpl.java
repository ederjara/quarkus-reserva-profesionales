package com.ejara.platform.application;

import com.ejara.platform.domain.model.entities.Profesional;
import com.ejara.platform.domain.model.repositories.ProfesionalRepository;
import com.ejara.platform.domain.model.services.ProfesionalService;
import com.ejara.platform.infraestructure.exceptions.handlers.BusinessException;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProfesionalServiceImpl implements ProfesionalService {

    @Inject
    ProfesionalRepository profesionalRepository;

    @Override
    public Uni<Profesional> crear(Profesional p) {
        p.setId(UUID.randomUUID());
        p.setEstadoActivo(true);
        return profesionalRepository.guardar(p);
    }

    @Override
    public Uni<Profesional> inactivar(UUID id) {
        return profesionalRepository.buscarPorId(id)
                .onItem().ifNull().failWith(new BusinessException("Profesional no encontrado"))
                .invoke(db -> {
                    db.setEstadoActivo(false);
                })
                .call(profesionalRepository::guardar);
    }

    @Override
    public Uni<List<Profesional>> listar() {
        return profesionalRepository.listar();
    }

    @Override
    public Uni<Profesional> buscarPorId(UUID id) {
        return profesionalRepository.buscarPorId(id)
                .onItem().ifNull().failWith(new BusinessException("Profesional no encontrado"));
    }
}
