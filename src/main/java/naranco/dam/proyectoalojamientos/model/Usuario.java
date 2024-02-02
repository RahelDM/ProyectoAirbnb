package naranco.dam.proyectoalojamientos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private int id;
    private String nombre;
    @Column(length = 10)
    private String password;
}

