package com.jsprm.parsermineria.services;

import com.jsprm.parsermineria.models.entities.ProductoEnFactura;
import com.jsprm.parsermineria.repositories.ProductoEnFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductoEnFacturaServiceImpl extends GenericoDAOImpl<ProductoEnFactura, ProductoEnFacturaRepository> implements ProductoEnFacturaService{

    @Autowired
    public ProductoEnFacturaServiceImpl(ProductoEnFacturaRepository repository) {
        super(repository);
    }
}
