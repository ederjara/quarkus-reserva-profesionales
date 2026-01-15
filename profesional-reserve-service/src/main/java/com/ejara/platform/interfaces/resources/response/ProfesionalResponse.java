package com.ejara.platform.interfaces.resources.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ProfesionalResponse (
    UUID id,
    String nombres,
    String apellidos,
    String especialidad,
    boolean estadoActivo
){}
