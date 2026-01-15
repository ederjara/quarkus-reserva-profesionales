package com.ejara.platform.interfaces.rest;

import com.ejara.platform.domain.model.services.ClienteService;
import com.ejara.platform.infraestructure.mappers.ClienteMapper;
import com.ejara.platform.interfaces.resources.request.ClienteRequest;
import com.ejara.platform.interfaces.resources.response.ClienteResponse;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@WithTransaction
@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
    ClienteService service;

    @POST
    public Uni<Response> crear(@Valid ClienteRequest request) {
        return service.crear(ClienteMapper.toDomain(request))
                .map(ClienteMapper::toResponse)
                .map(r -> Response.status(Response.Status.CREATED).entity(r).build());
    }

    @GET
    public Uni<Response> listar() {
        return service.listar()
                .map(list -> list.stream()
                        .map(ClienteMapper::toResponse)
                        .toList())
                .map(list -> Response.ok(list).build());


    }

    @PUT
    @Path("/{id}/inactivar")
    public Uni<Response> inactivar(@PathParam("id") UUID id) {
        return service.inactivar(id)
                .map(ClienteMapper::toResponse)
                .map(r -> Response.status(Response.Status.CREATED).entity(r).build());
    }
}
