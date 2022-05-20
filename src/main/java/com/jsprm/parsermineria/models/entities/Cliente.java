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
@Table(name = "clientes")
public class Cliente implements Serializable {
    @Id
    @SequenceGenerator(
            name = "cliente_sequence",
            sequenceName= "cliente_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "cliente_sequence"
    )
    @Column(name = "id_cliente", updatable = false)
    private Long idCliente;

    @Column(name = "numero_cliente", nullable = false)
    private String numeroCliente;

    @Column(name = "pais_cliente", nullable = false)
    private String paisCliente;

    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @Column(name = "fecha_alta", nullable = false)
    private Date fechaAlta;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;


    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"cliente"})
    private List<Factura> facturas;


    public Cliente(String numeroCliente, String paisCliente) {
        this.numeroCliente = numeroCliente;
        this.paisCliente = paisCliente;
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
    private static final long serialVersionUID = -9038909073506915423L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cliente cliente = (Cliente) o;
        return idCliente != null && Objects.equals(idCliente, cliente.idCliente);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
