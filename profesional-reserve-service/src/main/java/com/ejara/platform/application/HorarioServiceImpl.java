package com.ejara.platform.application;

import com.ejara.platform.domain.model.entities.Horario;
import com.ejara.platform.domain.model.repositories.HorarioRepository;
import com.ejara.platform.domain.model.services.HorarioService;
import com.ejara.platform.infraestructure.exceptions.handlers.BusinessException;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class HorarioServiceImpl implements HorarioService {

    @Inject
    HorarioRepository horarioRepository;

    @Override
    public Uni<Horario> registrar(Horario h) {
        h.setId(UUID.randomUUID());
        h.setEstado(true);

        return horarioRepository.existeSolapamiento(h)
                .flatMap(solapa -> solapa
                        ? Uni.createFrom()
                        .failure(new BusinessException("Existe un horario solapado para el profesional"))
                        : horarioRepository.guardar(h)
                );

    }

    @Override
    public Uni<List<Horario>> listar() {
        return horarioRepository.listar();
    }
}