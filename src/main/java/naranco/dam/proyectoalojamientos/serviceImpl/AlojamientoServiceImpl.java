package naranco.dam.proyectoalojamientos.serviceImpl;

import jakarta.transaction.Transactional;
import naranco.dam.proyectoalojamientos.model.Alojamiento;
import naranco.dam.proyectoalojamientos.respository.AlojamientoRepository;
import naranco.dam.proyectoalojamientos.service.AlojamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@Transactional
public class AlojamientoServiceImpl implements AlojamientoService {

    @Autowired
    AlojamientoRepository alojamientoRepository;

    @Override
    public List<Alojamiento> getAlojamientos() {
        return alojamientoRepository.findAll();
    }

    @Override
    public void insertar(Set<Alojamiento> alojamientos) {
        alojamientoRepository.saveAll(alojamientos);
    }

    @Override
    public List<Alojamiento> getAlojamientosByDistrito(Long idDistrito) {
        return alojamientoRepository.getAlojamientosByDistrito(idDistrito);
    }

    @Override
    public List<Alojamiento> getAlojamientosByDistritoMinYMax(Long id, double min, double max) {
        return alojamientoRepository.getAlojamientosByDistritoMaxMin(id, min, max);
    }

    @Override
    public List<Alojamiento> getAlojamientoByDistritoHabitaciones(Long id, int numHabitacion) {
        return alojamientoRepository.getAlojamientoByDistritoHabitaciones(id,numHabitacion);
    }

    @Override
    public List<Alojamiento> getAlojamientoByDistritoHabitacionesYBanos(Long id, int numHabitacion, int banos) {
        return alojamientoRepository.getAlojamientoByDistritoHabitacionesYBanos(id,numHabitacion,banos);
    }

    @Override
    public List<Alojamiento> getAlojamientoByDistritoCalificacion(Long id, double minCalificacion, double maxCalificacion) {
        return alojamientoRepository.getAlojamientoByDistritoCalificacion(id,minCalificacion,maxCalificacion);
    }

    @Override
    public List<Alojamiento> getAlojamientoByDistritoCompleto(Long id, double minCompleta, double maxCompleta, double minCalificacionCompleta, double maxCalificacionCompleta) {
        return alojamientoRepository.getAlojamientoByDistritoCompleto(id,minCompleta,maxCompleta,minCalificacionCompleta,maxCalificacionCompleta);
    }
}
