package naranco.dam.proyectoalojamientos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "distritos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Distrito {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "distrito")
    @JsonIgnore
    private Set<Barrio> barrios = new LinkedHashSet<>();


    public Distrito(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Distrito(String nombreDistrito) {
        this.nombre=nombreDistrito;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Distrito{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", barrios=").append(barrios);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Distrito distrito)) return false;
        return Objects.equals(getId(), distrito.getId()) && Objects.equals(getNombre(), distrito.getNombre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre());
    }
}

