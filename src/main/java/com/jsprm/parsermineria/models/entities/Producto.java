package com.jsprm.parsermineria.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto implements Serializable {
    @Id
    @SequenceGenerator(
            name = "producto_sequence",
            sequenceName= "producto_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "producto_sequence"
    )
    @Column(name = "id_producto", updatable = false)
    private Long idProducto;

    @Column(name = "numero_producto", nullable = false)
    private String numeroProducto;

    @Column(name = "descripcion_producto")
    private String descripcionProducto;

    @Column(name = "precio_unitario_producto_USD")
    private Double precioUnitarioProductoUSD;

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"producto"})
    private List<ProductoEnFactura> productosEnFactura;

    @Serial
    private static final long serialVersionUID = -7481144008893980673L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Producto producto = (Producto) o;
        return idProducto != null && Objects.equals(idProducto, producto.idProducto);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
