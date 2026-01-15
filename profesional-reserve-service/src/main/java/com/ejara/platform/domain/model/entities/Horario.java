package com.ejara.platform.domain.model.entities;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Horario extends PanacheEntityBase {

    @Id
    private UUID id;

    @ManyToOne
    private Profesional profesional;

    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    private boolean estado;
}
