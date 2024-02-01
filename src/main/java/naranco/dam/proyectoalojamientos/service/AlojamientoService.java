package naranco.dam.proyectoalojamientos.service;

import naranco.dam.proyectoalojamientos.model.Alojamiento;
import naranco.dam.proyectoalojamientos.model.Distrito;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface AlojamientoService {

    List<Alojamiento> getAlojamientos();
    void insertar(Set<Alojamiento> alojamientos);
    List<Alojamiento>  getAlojamientosByDistrito(Long idDistrito);

    List<Alojamiento> getAlojamientosByDistritoMinYMax(Long id, double min, double max);

    List<Alojamiento> getAlojamientoByDistritoHabitaciones(Long id, int numHabitacion);

    List<Alojamiento> getAlojamientoByDistritoHabitacionesYBanos(Long id, int numHabitacion, int banos);

    List<Alojamiento> getAlojamientoByDistritoCalificacion(Long id,  double minCalificacion, double maxCalificacion);

    List<Alojamiento> getAlojamientoByDistritoCompleto(Long id, double minCompleta, double maxCompleta, double minCalificacionCompleta, double maxCalificacionCompleta);
}
