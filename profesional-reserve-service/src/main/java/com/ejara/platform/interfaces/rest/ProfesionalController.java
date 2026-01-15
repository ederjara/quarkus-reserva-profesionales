package com.ejara.platform.interfaces.rest;

import com.ejara.platform.domain.model.services.ProfesionalService;
import com.ejara.platform.infraestructure.mappers.ProfesionalMapper;
import com.ejara.platform.interfaces.resources.request.ProfesionalRequest;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@WithTransaction
@Path("/profesionales")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfesionalController {
    @Inject
    ProfesionalService service;

    @POST
    public Uni<Response> crear(@Valid ProfesionalRequest request) {
        return service.crear(ProfesionalMapper.toDomain(request))
                .map(ProfesionalMapper::toResponse)
                .map(r ->
                        Response.status(Response.Status.CREATED)
                        .entity(r)
                        .build()
                );
    }


    @GET
    public Uni<Response> listar() {
        return service.listar()
                .map(list -> list.stream()
                        .map(ProfesionalMapper::toResponse)
                        .toList())
                .map(list -> Response.ok(list).build());
    }

    @PUT
    @Path("/{id}/inactivar")
    public Uni<Response> inactivar(@PathParam("id") UUID id) {
        return service.inactivar(id)
                .map(ProfesionalMapper::toResponse)
                .map(r -> Response.status(Response.Status.CREATED).entity(r).build());
    }
}
