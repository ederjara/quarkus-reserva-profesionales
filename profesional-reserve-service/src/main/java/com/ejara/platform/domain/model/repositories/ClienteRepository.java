package com.ejara.platform.domain.model.repositories;

import com.ejara.platform.domain.model.entities.Cliente;
import io.smallrye.mutiny.Uni;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository {
    Uni<Cliente> guardar(Cliente c);
    Uni<Cliente> buscarPorId(UUID id);
    Uni<List<Cliente>> listar();
}
