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
@Table(name = "requests")
public class Request implements Serializable {
    @Id
    @SequenceGenerator(
            name = "request_sequence",
            sequenceName= "request_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "request_sequence"
    )
    @Column(name = "id_request", updatable = false)
    private Long idRequest;

    @Column(name = "response_status", nullable = false)
    private Integer responseStatus;

    @Column(name = "fecha_alta", nullable = false)
    private Date fechaAlta;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"request"})
    private List<Path> paths;

    @PrePersist
    private void antesPersistir(){
        this.fechaAlta = new Date();
    }

    @Serial
    private static final long serialVersionUID = 6942637018799207053L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Request request = (Request) o;
        return idRequest != null && Objects.equals(idRequest, request.idRequest);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

