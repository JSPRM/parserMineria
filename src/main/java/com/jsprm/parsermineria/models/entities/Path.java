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
@Table(name = "Paths")
public class Path implements Serializable {
    @Id
    @SequenceGenerator(
            name = "path_sequence",
            sequenceName= "path_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "path_sequence"
    )
    @Column(name = "id_path", updatable = false)
    private Long idPath;

    @Column(name = "local_path", nullable = false)
    private String localPath;

    @Column(name = "persiste")
    private Boolean persiste;

    @Column(name = "detalle")
    private String detalle;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_request", referencedColumnName = "id_request", foreignKey = @ForeignKey(name = "FK_REQUEST_ID"))
    private Request request;

    public Path(String localPath) {
        this.localPath = localPath;
    }

    @Serial
    private static final long serialVersionUID = 6143803810779257328L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Path path = (Path) o;
        return idPath != null && Objects.equals(idPath, path.idPath);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
