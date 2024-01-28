package naranco.dam.proyectoalojamientos.model;

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
public class Distrito implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "distrito")
    private Set<Barrio> barrios = new LinkedHashSet<>();


    public Distrito(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Distrito(String nombreDistrito) {
        this.nombre=nombreDistrito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distrito distrito = (Distrito) o;
        return Objects.equals(id, distrito.id) && Objects.equals(nombre, distrito.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
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
}

