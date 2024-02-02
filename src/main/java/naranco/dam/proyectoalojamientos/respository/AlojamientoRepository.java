package naranco.dam.proyectoalojamientos.respository;

import naranco.dam.proyectoalojamientos.model.Alojamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AlojamientoRepository  extends JpaRepository<Alojamiento,Long> {

    @Query("SELECT a.id FROM alojamientos a WHERE a.nombre LIKE ?1")
    Long findByNombre(String nombre);
    @Query("SELECT a FROM alojamientos a WHERE a.barrio.distrito.id=?1")
    List<Alojamiento> getAlojamientosByDistrito(Long idDistrito);

    @Query("SELECT a FROM alojamientos a WHERE a.barrio.distrito.id=?1 AND a.precio BETWEEN ?2 AND ?3")
    List<Alojamiento> getAlojamientosByDistritoMaxMin(Long idDistrito, double min, double max);

    @Query("SELECT a FROM alojamientos a WHERE a.barrio.distrito.id=?1 AND a.habitaciones = ?2")
    List<Alojamiento> getAlojamientoByDistritoHabitaciones(Long id, int numHabitacion);
    @Query("SELECT a FROM alojamientos a WHERE a.barrio.distrito.id=?1 AND a.habitaciones = ?2 AND a.banos=?3")
    List<Alojamiento> getAlojamientoByDistritoHabitacionesYBanos(Long id, int numHabitacion, int banos);

    @Query("SELECT a FROM alojamientos a WHERE a.barrio.distrito.id=?1 AND a.puntuacion BETWEEN ?2 AND ?3")
    List<Alojamiento> getAlojamientoByDistritoCalificacion(Long id,  double minCalificacion, double maxCalificacion);

    @Query("SELECT a FROM alojamientos a WHERE a.barrio.distrito.id=?1 AND a.precio BETWEEN ?2 AND ?3 AND a.puntuacion BETWEEN ?4 AND ?5")
    List<Alojamiento> getAlojamientoByDistritoCompleto(Long id, double minCompleta, double maxCompleta, double minCalificacionCompleta, double maxCalificacionCompleta);

}
