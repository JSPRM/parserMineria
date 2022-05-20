package com.jsprm.parsermineria.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="productosenfactura")
public class ProductoEnFactura implements Serializable {
    @Id
    @SequenceGenerator(
            name = "producto_en_factura_sequence",
            sequenceName= "producto_en_factura_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "producto_en_factura_sequence"
    )
    @Column(name = "id_producto_en_factura",updatable = false)
    private Long idProductoEnFactura;

    @Column(name = "cantidad_producto_en_factura")
    private Integer cantidadProductoEnFactura;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "numero_factura", referencedColumnName = "numero_factura", foreignKey = @ForeignKey(name = "FK_NUMERO_FACTURA"))
    private Factura factura;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "numero_producto", referencedColumnName = "numero_producto", foreignKey = @ForeignKey(name = "FK_NUMERO_PRODUCTO"))
    private Producto producto;

    public ProductoEnFactura(Integer cantidadProductoEnFactura, Factura factura, Producto producto) {
        this.cantidadProductoEnFactura = cantidadProductoEnFactura;
        this.factura = factura;
        this.producto = producto;
    }

    @Serial
    private static final long serialVersionUID = 2435118103840188124L;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductoEnFactura that = (ProductoEnFactura) o;
        return idProductoEnFactura != null && Objects.equals(idProductoEnFactura, that.idProductoEnFactura);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
