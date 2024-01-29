package naranco.dam.proyectoalojamientos.respository;

import naranco.dam.proyectoalojamientos.model.Alojamiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AlojamientoRepository  extends JpaRepository<Alojamiento,Long> {



}
