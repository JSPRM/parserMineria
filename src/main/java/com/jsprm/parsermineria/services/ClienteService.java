package com.jsprm.parsermineria.services;

import com.jsprm.parsermineria.models.entities.Cliente;

import java.util.Optional;

public interface ClienteService extends GenericoDAO<Cliente>{
    Optional<Cliente> buscarPorNumero(String numeroCliente);
}
