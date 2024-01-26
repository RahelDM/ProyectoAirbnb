package naranco.dam.proyectoalojamientos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tiposHabitacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoHabitacion {
    @Id
    private Long id;

    @Column(name = "nombre")
    private String nombre;
}