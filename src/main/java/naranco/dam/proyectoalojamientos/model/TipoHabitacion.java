package naranco.dam.proyectoalojamientos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "tiposHabitacion")
@Getter
@Setter
@NoArgsConstructor
public class TipoHabitacion {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;


    @OneToMany(mappedBy = "tipoHabitacion")
    private Set<Alojamiento> alojamientos = new LinkedHashSet<Alojamiento>();


    public TipoHabitacion(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public TipoHabitacion(String nombreHabitacion) {
        this.nombre = nombreHabitacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoHabitacion that = (TipoHabitacion) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TipoHabitacion{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
       // sb.append("alojamentos: ").append(alojamientos);
        sb.append('}');
        return sb.toString();
    }
}