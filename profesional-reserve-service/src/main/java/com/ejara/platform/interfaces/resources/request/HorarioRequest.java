package com.ejara.platform.interfaces.resources.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Builder
public record HorarioRequest (
    @NotNull
    UUID profesionalId,

    @NotNull
    LocalDate fecha,

    @NotNull
    LocalTime horaInicio,

    @NotNull
    LocalTime horaFin
){}
