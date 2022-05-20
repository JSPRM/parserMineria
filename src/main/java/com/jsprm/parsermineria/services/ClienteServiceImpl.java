package com.jsprm.parsermineria.services;

import com.jsprm.parsermineria.models.entities.Cliente;
import com.jsprm.parsermineria.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteServiceImpl extends GenericoDAOImpl<Cliente, ClienteRepository> implements ClienteService{


    @Autowired
    public ClienteServiceImpl(ClienteRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Optional<Cliente> buscarPorNumero(String numeroCliente) {
        return repository.buscarPorNumero(numeroCliente);
        
    }


}
