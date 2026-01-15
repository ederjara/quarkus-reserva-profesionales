package com.ejara.platform.interfaces.resources.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Builder
public record HorarioResponse (
    UUID id,
    String profesional,
    LocalDate fecha,
    LocalTime horaInicio,
    LocalTime horaFin,
    boolean estado
){}
