package com.ejara.platform.interfaces.rest;

import com.ejara.platform.domain.model.services.ClienteService;
import com.ejara.platform.domain.model.services.ProfesionalService;
import com.ejara.platform.domain.model.services.ReservaService;
import com.ejara.platform.infraestructure.mappers.ReservaMapper;
import com.ejara.platform.interfaces.resources.request.ReservaRequest;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@WithTransaction
@Path("/reservas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservaController {

    @Inject
    ReservaService reservaService;

    @Inject
    ClienteService clienteService;

    @Inject
    ProfesionalService profesionalService;

    @POST
    public Uni<Response> crear(@Valid ReservaRequest request) {
        return clienteService.buscarPorId(request.clienteId())
                .flatMap(c ->
                        profesionalService.buscarPorId(request.profesionalId())
                                .map(p -> ReservaMapper.toDomain(request, c, p))
                )
                .flatMap(reservaService::crear)
                .map(ReservaMapper::toResponse)
                .map(r -> Response.status(Response.Status.CREATED).entity(r).build());
    }

    @PUT
    @Path("/{id}/cancelar")
    public Uni<Response> cancelar(@PathParam("id") UUID id) {
        return reservaService.cancelar(id)
                .map(v -> Response.noContent().build());
    }

    @GET
    @Path("ranking-profesionales")
    public Uni<Response> rankingProfesionales() {
        return reservaService.listarProfesionalesPorReservasActivas()
                .map(list -> Response.ok(list).build());
    }
}

