package com.ejara.platform.infraestructure.repositories;

import com.ejara.platform.domain.model.entities.Reserva;
import com.ejara.platform.domain.model.repositories.ReservaRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ReservaRepositoryImpl implements ReservaRepository {
    @Override
    public Uni<Boolean> existeSolapamiento(Reserva r) {
        return Reserva.find(
                        "profesional = ?1 and fecha = ?2 and estado = 'CREADA' " +
                                "and (horaInicio < ?4 and horaFin > ?3)",
                        r.getProfesional(), r.getFecha(), r.getHoraInicio(), r.getHoraFin()
                ).count()
                .map(c -> c > 0);
    }

    @Override
    public Uni<Reserva> guardar(Reserva r) {
        return r.persist();
    }

    @Override
    public Uni<Reserva> buscarPorId(UUID id) {
        return Reserva.findById(id);
    }

    @Override
    public Uni<List<Reserva>> reservasActivas() {
        return Reserva.list("estado = 'CREADA'");
    }
}
