package naranco.dam.proyectoalojamientos.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "alojamientos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alojamiento implements Serializable {

    @Id
    private Long id;

    @Column(name = "aseos", nullable = false)
    private int aseos;

    @Column(name = "banos", nullable = false)
    private int banos;

    @Column(name = "camas", nullable = false)
    private int camas;

    @Column(name = "habitaciones", nullable = false)
    private int habitaciones;

    @Column(name = "latitud", nullable = false)
    private double latitud;

    @Column(name = "longitud", nullable = false)
    private double longitud;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "puntuacion", nullable = false)
    private double puntuacion;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToOne()
    @JoinColumn(name = "barrio_id")
    private Barrio barrio;

    @ManyToOne()
    @JoinColumn(name = "tipoHabitacion_id")
    private TipoHabitacion tipoHabitacion;

    @ManyToOne()
    @JoinColumn(name = "propietario_id")
    private Propietario propietario;

    public Alojamiento(Long id, int aseos, int banos, int camas, int habitaciones, double latitud, double longitud, double precio, double puntuacion, String nombre) {
        this.id = id;
        this.aseos = aseos;
        this.banos = banos;
        this.camas = camas;
        this.habitaciones = habitaciones;
        this.latitud = latitud;
        this.longitud = longitud;
        this.precio = precio;
        this.puntuacion = puntuacion;
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alojamiento that = (Alojamiento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Alojamiento{");
        sb.append("id=").append(id);
        sb.append(", aseos=").append(aseos);
        sb.append(", banos=").append(banos);
        sb.append(", camas=").append(camas);
        sb.append(", habitaciones=").append(habitaciones);
        sb.append(", latitud=").append(latitud);
        sb.append(", longitud=").append(longitud);
        sb.append(", precio=").append(precio);
        sb.append(", puntuacion=").append(puntuacion);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", barrio=").append(barrio);
        sb.append(", tipoHabitacion=").append(tipoHabitacion);
        sb.append(", propietario=").append(propietario);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}