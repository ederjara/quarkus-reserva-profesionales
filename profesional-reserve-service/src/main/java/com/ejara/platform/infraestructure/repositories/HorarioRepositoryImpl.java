package com.ejara.platform.infraestructure.repositories;

import com.ejara.platform.domain.model.entities.Horario;
import com.ejara.platform.domain.model.entities.Reserva;
import com.ejara.platform.domain.model.repositories.HorarioRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class HorarioRepositoryImpl implements HorarioRepository {

    @Override
    public Uni<Boolean> existeSolapamiento(Horario h) {
        return Horario.find(
                        "profesional = ?1 and fecha = ?2 and estado = true " +
                                "and (horaInicio < ?4 and horaFin > ?3)",
                        h.getProfesional(), h.getFecha(), h.getHoraInicio(), h.getHoraFin()
                ).count()
                .map(c -> c > 0);
    }

    @Override
    public Uni<Boolean> cubreHorario(Reserva r) {
        return Horario.find(
                        "profesional = ?1 and fecha = ?2 and estado = true " +
                                "and horaInicio <= ?3 and horaFin >= ?4",
                        r.getProfesional(), r.getFecha(), r.getHoraInicio(), r.getHoraFin()
                ).count()
                .map(c -> c > 0);
    }

    @Override
    public Uni<Horario> guardar(Horario h) {
        return h.persist();
    }

    @Override
    public Uni<List<Horario>> listar() {
        return Horario.listAll();
    }
}
