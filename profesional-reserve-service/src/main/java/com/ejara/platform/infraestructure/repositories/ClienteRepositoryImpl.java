package com.ejara.platform.infraestructure.repositories;

import com.ejara.platform.domain.model.entities.Cliente;
import com.ejara.platform.domain.model.repositories.ClienteRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ClienteRepositoryImpl implements ClienteRepository {

    @Override
    public Uni<Cliente> guardar(Cliente c) {
        return c.persist();
    }

    @Override
    public Uni<Cliente> buscarPorId(UUID id) {
        return Cliente.findById(id);
    }

    @Override
    public Uni<List<Cliente>> listar() {
        return Cliente.listAll();
    }
}