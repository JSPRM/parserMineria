package com.jsprm.parsermineria.repositories;

import com.jsprm.parsermineria.models.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("select c from Cliente c where c.numeroCliente = ?1")
    Optional<Cliente> buscarPorNumero(String numeroCliente);
}
