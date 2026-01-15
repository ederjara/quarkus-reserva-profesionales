package com.ejara.platform.interfaces.rest;

import com.ejara.platform.domain.model.services.HorarioService;
import com.ejara.platform.domain.model.services.ProfesionalService;
import com.ejara.platform.infraestructure.mappers.HorarioMapper;
import com.ejara.platform.interfaces.resources.request.HorarioRequest;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@WithTransaction
@Path("/horarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HorarioController {

    @Inject
    HorarioService horarioService;

    @Inject
    ProfesionalService profesionalService;

    @POST
    public Uni<Response> registrar(@Valid HorarioRequest request) {
        return profesionalService.buscarPorId(request.profesionalId())
                .map(p -> HorarioMapper.toDomain(request, p))
                .flatMap(horarioService::registrar)
                .map(HorarioMapper::toResponse)
                .map(r -> Response.status(Response.Status.CREATED).entity(r).build());
    }

    @GET
    public Uni<Response> listar() {
        return horarioService.listar()
                .map(list -> list.stream()
                        .map(HorarioMapper::toResponse)
                        .toList())
                .map(list -> Response.ok(list).build());
    }
}
