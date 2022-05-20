package com.jsprm.parsermineria.services;

import com.jsprm.parsermineria.models.entities.Producto;

import java.util.Optional;

public interface ProductoService extends GenericoDAO<Producto>{
    Optional<Producto> buscarPorNumero(String numeroProducto);
}
