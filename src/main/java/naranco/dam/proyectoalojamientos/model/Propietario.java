
package naranco.dam.proyectoalojamientos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "propietarios")
@Getter
@Setter
@NoArgsConstructor
public class Propietario {
    @Id
    @Column(name ="id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "propietario")
    private Set<Alojamiento> alojamientos = new LinkedHashSet<Alojamiento>();

    public Propietario(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Propietario(String nombrePropietario) {
        this.nombre = nombrePropietario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Propietario that = (Propietario) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Propietario{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
     //   sb.append(", alojamientos=").append(alojamientos);
        sb.append('}');
        return sb.toString();
    }
}