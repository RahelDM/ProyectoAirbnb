
package naranco.dam.proyectoalojamientos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "barrios")
@Getter
@Setter
@NoArgsConstructor
public class Barrio {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    @Column(name = "nombre")
    private String nombre;

    @ManyToOne()
    @JoinColumn(name = "distrito_id")
    @JsonIgnore
    private Distrito distrito;

    @OneToMany(mappedBy = "barrio")
    @JsonIgnore
    private Set<Alojamiento> alojamientos = new LinkedHashSet<>();


    public Barrio(Long id, String nombre, Distrito distrito) {
        this.id = id;
        this.nombre = nombre;
        this.distrito = distrito;
    }

    public Barrio(String nombreBarrio, Distrito distrito) {
        this.nombre=nombreBarrio;
        this.distrito = distrito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barrio barrio = (Barrio) o;
        return Objects.equals(id, barrio.id) && Objects.equals(nombre, barrio.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Barrio{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", distrito=").append(distrito);
        sb.append('}');
        return sb.toString();
    }
}
