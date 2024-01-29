package naranco.dam.proyectoalojamientos.respository;

import naranco.dam.proyectoalojamientos.model.Barrio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface BarrioRespository  extends JpaRepository<Barrio,Long> {

    @Query("SELECT b.id FROM barrios b WHERE b.nombre=?1")
    Long findByNombre(String nombre);
}
