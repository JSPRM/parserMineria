package com.jsprm.parsermineria.services;

import java.util.Optional;

public interface GenericoDAO<E>{
    Optional<E> buscarPorId(Long id);
    E guardar(E entidad);
    Iterable<E> buscarTodos();
    void eliminarPorId(Long id);
    Boolean validacion(E entidad);
}
