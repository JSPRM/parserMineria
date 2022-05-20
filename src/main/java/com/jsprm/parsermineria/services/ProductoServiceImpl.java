package com.jsprm.parsermineria.services;

import com.jsprm.parsermineria.models.entities.Producto;
import com.jsprm.parsermineria.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class ProductoServiceImpl extends GenericoDAOImpl<Producto, ProductoRepository> implements ProductoService{

    @Autowired
    public ProductoServiceImpl(ProductoRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Optional<Producto> buscarPorNumero(String numeroProducto) {
        return repository.buscarPorNumero(numeroProducto);
    }
}
