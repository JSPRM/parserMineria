package com.jsprm.parsermineria.services;

import com.jsprm.parsermineria.exceptions.EntidadNoValida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class GenericoDAOImpl<E, R extends JpaRepository<E, Long>> implements GenericoDAO<E> {


    protected final R repository;

    public GenericoDAOImpl(R repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public E guardar(E entidad) {
        if(validacion(entidad)){
            return repository.save(entidad);
        }else{
            throw new EntidadNoValida("Persistencia fallida");
        }
    }

    @Override
    public Iterable<E> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void eliminarPorId(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Boolean validacion(E entidad) {
        return true;
    }
}
