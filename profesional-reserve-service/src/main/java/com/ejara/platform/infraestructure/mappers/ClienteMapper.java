package com.ejara.platform.infraestructure.mappers;

import com.ejara.platform.domain.model.entities.Cliente;
import com.ejara.platform.interfaces.resources.request.ClienteRequest;
import com.ejara.platform.interfaces.resources.response.ClienteResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClienteMapper {

    public static Cliente toDomain(ClienteRequest r) {
        return Cliente.builder()
                .nombres(r.nombres())
                .apellidos(r.apellidos())
                .email(r.email())
                .telefono(r.telefono())
                .build();
    }

    public static ClienteResponse toResponse(Cliente c) {
        return ClienteResponse.builder()
                .id(c.getId())
                .nombres(c.getNombres())
                .apellidos(c.getApellidos())
                .email(c.getEmail())
                .telefono(c.getTelefono())
                .estadoActivo(c.isEstadoActivo())
                .build();
    }
}
