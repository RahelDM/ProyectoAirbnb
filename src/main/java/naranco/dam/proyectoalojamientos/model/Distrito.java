package naranco.dam.proyectoalojamientos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "distritos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Distrito {

    @Id
    private Long id;

    @Column(name = "nombre")
    private String nombre;
}
