package com.ejara.platform.interfaces.resources.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ProfesionalRequest (
    @NotBlank
    String nombres,

    @NotBlank
    String apellidos,

    @NotBlank
    String especialidad
){}
