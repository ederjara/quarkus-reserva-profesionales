package com.ejara.platform.domain.model.entities;

import com.ejara.platform.domain.model.enums.ReservaEstado;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
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
public class Reserva extends PanacheEntityBase {

    @Id
    private UUID id;

    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Profesional profesional;

    @Enumerated(EnumType.STRING)
    private ReservaEstado estado;
}
