package naranco.dam.proyectoalojamientos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "alojamientos")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class Alojamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}