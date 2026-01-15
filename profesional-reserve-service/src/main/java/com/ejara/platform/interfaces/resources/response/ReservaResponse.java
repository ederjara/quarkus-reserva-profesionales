package com.ejara.platform.interfaces.resources.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Builder
public record ReservaResponse (
    UUID id,
    LocalDate fecha,
    LocalTime horaInicio,
    LocalTime horaFin,
    String estado,
    String cliente,
    String profesional
){}
