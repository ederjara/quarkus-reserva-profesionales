package com.ejara.platform.application;

import com.ejara.platform.domain.model.entities.Cliente;
import com.ejara.platform.domain.model.repositories.ClienteRepository;
import com.ejara.platform.domain.model.services.ClienteService;
import com.ejara.platform.infraestructure.exceptions.handlers.BusinessException;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    @Override
    public Uni<Cliente> crear(Cliente c) {
        c.setId(UUID.randomUUID());
        c.setEstadoActivo(true);
        return clienteRepository.guardar(c);
    }

    @Override
    public Uni<Cliente> inactivar(UUID id) {
        return clienteRepository.buscarPorId(id)
                .onItem().ifNull().failWith(new BusinessException("Cliente no encontrado"))
                .invoke(db -> {
                    db.setEstadoActivo(false);
                })
                .call(clienteRepository::guardar);
    }

    @Override
    public Uni<Cliente> buscarPorId(UUID id) {
        return clienteRepository.buscarPorId(id)
                .onItem().ifNull().failWith(new BusinessException("Cliente no encontrado"));
    }

    @Override
    public Uni<List<Cliente>> listar() {
        return clienteRepository.listar();
    }
}