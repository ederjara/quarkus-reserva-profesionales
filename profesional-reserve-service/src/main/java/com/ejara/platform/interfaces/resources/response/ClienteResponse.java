package com.ejara.platform.interfaces.resources.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ClienteResponse (
    UUID id,
    String nombres,
    String apellidos,
    String email,
    String telefono,
    boolean estadoActivo
){}
