package com.jsprm.parsermineria.services;

import com.jsprm.parsermineria.models.entities.Factura;
import com.jsprm.parsermineria.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FacturaServiceImpl extends GenericoDAOImpl<Factura, FacturaRepository> implements FacturaService{

    @Autowired
    public FacturaServiceImpl(FacturaRepository repository) {
        super(repository);
    }


}
