package com.ejara.platform.infraestructure.mappers;

import com.ejara.platform.domain.model.entities.Cliente;
import com.ejara.platform.domain.model.entities.Profesional;
import com.ejara.platform.domain.model.entities.Reserva;
import com.ejara.platform.interfaces.resources.request.ReservaRequest;
import com.ejara.platform.interfaces.resources.response.ReservaDetalleRankingResponse;
import com.ejara.platform.interfaces.resources.response.ReservaResponse;

public class ReservaMapper {

    public static Reserva toDomain(ReservaRequest r, Cliente c, Profesional p) {
        return Reserva.builder()
                .cliente(c)
                .profesional(p)
                .fecha(r.fecha())
                .horaInicio(r.horaInicio())
                .horaFin(r.horaFin())
                .build();
    }

    public static ReservaResponse toResponse(Reserva r) {
        return ReservaResponse.builder()
                .id(r.getId())
                .fecha(r.getFecha())
                .horaInicio(r.getHoraInicio())
                .horaFin(r.getHoraFin())
                .estado(r.getEstado().name())
                .cliente("%s %s".formatted(r.getCliente().getApellidos(), r.getCliente().getNombres()))
                .profesional("%s %s".formatted(r.getProfesional().getApellidos(), r.getProfesional().getNombres()))
                .build();
    }

    public static ReservaDetalleRankingResponse mapearReservaDetalleRanking(Reserva r) {
        return ReservaDetalleRankingResponse.builder()
                .id(r.getId())
                .cliente("%s %s".formatted(r.getCliente().getApellidos(), r.getCliente().getNombres()))
                .horaInicio(r.getHoraInicio().toString())
                .horaFin(r.getHoraFin().toString())
                .build();
    }
}
