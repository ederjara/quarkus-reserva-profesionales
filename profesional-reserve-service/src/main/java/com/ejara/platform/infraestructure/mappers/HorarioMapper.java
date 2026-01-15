package com.ejara.platform.infraestructure.mappers;

import com.ejara.platform.domain.model.entities.Horario;
import com.ejara.platform.domain.model.entities.Profesional;
import com.ejara.platform.interfaces.resources.request.HorarioRequest;
import com.ejara.platform.interfaces.resources.response.HorarioResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HorarioMapper {

    public static Horario toDomain(HorarioRequest r, Profesional p) {
        return Horario.builder()
                .profesional(p)
                .fecha(r.fecha())
                .horaInicio(r.horaInicio())
                .horaFin(r.horaFin())
                .build();
    }

    public static HorarioResponse toResponse(Horario h) {
        return HorarioResponse.builder()
                .id(h.getId())
                .fecha(h.getFecha())
                .horaInicio(h.getHoraInicio())
                .horaFin(h.getHoraFin())
                .estado(h.isEstado())
                .profesional("%s %s".formatted(h.getProfesional().getApellidos(), h.getProfesional().getNombres()))
                .build();
    }
}
