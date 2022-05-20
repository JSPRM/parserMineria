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
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "facturas")
public class Factura implements Serializable {
    @Id
    @SequenceGenerator(
            name = "factura_sequence",
            sequenceName= "factura_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "factura_sequence"
    )
    @Column(name = "id_factura", updatable = false)
    private Long idFactura;

    @Column(name = "numero_factura", nullable = false)
    private String numeroFactura;

    @Column(name = "tipo_moneda", nullable = false)
    private String tipoMoneda;

    @Column(name = "fecha_factura", nullable = false)
    private String fechaFactura;

    @Column(name = "fecha_alta", nullable = false)
    private Date fechaAlta;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "numero_cliente", referencedColumnName = "numero_cliente", foreignKey = @ForeignKey(name = "FK_NUMERO_CLIENTE"))
    private Cliente cliente;


    @OneToMany(mappedBy = "factura", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"factura"})
    private List<ProductoEnFactura> productosEnFactura;


    public Factura(String numeroFactura, String tipoMoneda, String fechaFactura, Cliente cliente) {
        this.numeroFactura = numeroFactura;
        this.tipoMoneda = tipoMoneda;
        this.fechaFactura = fechaFactura;
        this.cliente = cliente;
    }

    @PrePersist
    private void antesDePersistir(){
        this.fechaAlta = new Date();
    }

    @PreUpdate
    private void antesDeModificar(){
        this.fechaModificacion = new Date();
    }

    @Serial
    private static final long serialVersionUID = -4556468051576187799L;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Factura factura = (Factura) o;
        return idFactura != null && Objects.equals(idFactura, factura.idFactura);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
