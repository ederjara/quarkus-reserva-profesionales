package com.ejara.platform.application;

import com.ejara.platform.domain.model.entities.Profesional;
import com.ejara.platform.domain.model.entities.Reserva;
import com.ejara.platform.domain.model.enums.ReservaEstado;
import com.ejara.platform.domain.model.repositories.HorarioRepository;
import com.ejara.platform.domain.model.repositories.ReservaRepository;
import com.ejara.platform.domain.model.services.ReservaService;
import com.ejara.platform.infraestructure.exceptions.handlers.BusinessException;
import com.ejara.platform.infraestructure.mappers.ReservaMapper;
import com.ejara.platform.interfaces.resources.response.FechaReservasResponse;
import com.ejara.platform.interfaces.resources.response.ProfesionalRankingResponse;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReservaServiceImpl implements ReservaService {

    @Inject
    ReservaRepository reservaRepository;

    @Inject
    HorarioRepository horarioRepository;

    @Override
    public Uni<Reserva> crear(Reserva r) {

        if (!r.getCliente().isEstadoActivo() || !r.getProfesional().isEstadoActivo()) {
            throw new BusinessException("Cliente o profesional no está activo");
        }

        r.setId(UUID.randomUUID());
        r.setEstado(ReservaEstado.CREADA);

        return horarioRepository.cubreHorario(r)
                .flatMap(cubre -> {
                    if (!cubre) {
                        return Uni.createFrom()
                                .failure(new BusinessException("El horario no cubre la reserva"));
                    }
                    return Uni.createFrom().item(r);
                })
                .flatMap(v -> reservaRepository.existeSolapamiento(r))
                .flatMap(existe -> {
                    if (existe) {
                        return Uni.createFrom()
                                .failure(new BusinessException("Existe un horario solapado para el profesional"));
                    }
                    return Uni.createFrom().item(r);
                })
                .call(reservaRepository::guardar);
    }

    @Override
    public Uni<Void> cancelar(UUID id) {
        return reservaRepository.buscarPorId(id)
                .onItem().ifNull().failWith(new BusinessException("Reserva no encontrada"))
                .invoke(r -> r.setEstado(ReservaEstado.CANCELADA))
                .call(reservaRepository::guardar)
                .replaceWithVoid();
    }

    @Override
    public Uni<List<ProfesionalRankingResponse>> listarProfesionalesPorReservasActivas() {
        return reservaRepository.reservasActivas()
                .map(this::procesarRanking);
    }

    private List<ProfesionalRankingResponse> procesarRanking(List<Reserva> reservas) {

        Map<Profesional, List<Reserva>> porProfesional =
                reservas.stream()
                        .collect(Collectors.groupingBy(Reserva::getProfesional));

        return porProfesional.entrySet()
                .stream()
                .map(entry -> construirRanking(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(
                        ProfesionalRankingResponse::reservasActivas).reversed())
                .toList();
    }

    private ProfesionalRankingResponse construirRanking(
            Profesional profesional,
            List<Reserva> reservas) {

        Map<LocalDate, List<Reserva>> reservasPorFecha =
                reservas.stream()
                        .collect(Collectors.groupingBy(
                                Reserva::getFecha
                        ));


        List<FechaReservasResponse> fechas =
                reservasPorFecha.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByKey())
                        .map(entry -> new FechaReservasResponse(
                                entry.getKey(),
                                entry.getValue()
                                        .stream()
                                        .map(ReservaMapper::mapearReservaDetalleRanking)
                                        .toList()
                        ))
                        .toList();

        return new ProfesionalRankingResponse(
                profesional.getId(),
                "%s %s".formatted(profesional.getApellidos(), profesional.getNombres()),
                (long) reservas.size(),
                fechas
        );
    }
}
