package com.ejara.platform.infraestructure.mappers;

import com.ejara.platform.domain.model.entities.Profesional;
import com.ejara.platform.interfaces.resources.request.ProfesionalRequest;
import com.ejara.platform.interfaces.resources.response.ProfesionalResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProfesionalMapper {

    public static Profesional toDomain(ProfesionalRequest r) {
        return Profesional.builder()
                .nombres(r.nombres())
                .apellidos(r.apellidos())
                .especialidad(r.especialidad())
                .build();
    }

    public static ProfesionalResponse toResponse(Profesional p) {
        return ProfesionalResponse.builder()
                .id(p.getId())
                .nombres(p.getNombres())
                .apellidos(p.getApellidos())
                .especialidad(p.getEspecialidad())
                .estadoActivo(p.isEstadoActivo())
                .build();
    }
}
