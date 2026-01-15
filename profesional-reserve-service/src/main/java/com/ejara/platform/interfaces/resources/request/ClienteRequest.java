package com.ejara.platform.interfaces.resources.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ClienteRequest (
    @NotBlank
    String nombres,

    @NotBlank
    String apellidos,

    @Email
    @NotBlank
    String email,

    String telefono
){}

