package com.ejara.platform.domain.model.services;

import com.ejara.platform.domain.model.entities.Cliente;
import io.smallrye.mutiny.Uni;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
    Uni<Cliente> crear(Cliente cliente);

    Uni<Cliente> inactivar(UUID id);

    Uni<Cliente> buscarPorId(UUID id);

    Uni<List<Cliente>> listar();
}
