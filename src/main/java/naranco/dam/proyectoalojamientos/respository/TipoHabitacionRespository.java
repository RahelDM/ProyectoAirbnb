package naranco.dam.proyectoalojamientos.respository;

import naranco.dam.proyectoalojamientos.model.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface TipoHabitacionRespository  extends JpaRepository<TipoHabitacion,Long> {

    @Query("SELECT t.id FROM tiposHabitacion t WHERE t.nombre=?1")
    Long findByNombre(String nombre);
}
