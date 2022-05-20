package com.jsprm.parsermineria.repositories;

import com.jsprm.parsermineria.models.entities.ProductoEnFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoEnFacturaRepository extends JpaRepository<ProductoEnFactura, Long> {
}
