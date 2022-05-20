package com.jsprm.parsermineria.repositories;

import com.jsprm.parsermineria.models.entities.Cliente;
import com.jsprm.parsermineria.models.entities.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepositoryImplementation<Producto, Long> {
    @Query("select p from Producto p where p.numeroProducto = ?1")
    Optional<Producto> buscarPorNumero(String numeroProducto);
}
