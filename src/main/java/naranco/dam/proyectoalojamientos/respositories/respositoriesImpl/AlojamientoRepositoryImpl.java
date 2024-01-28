package naranco.dam.proyectoalojamientos.respositories.respositoriesImpl;

import naranco.dam.proyectoalojamientos.model.Alojamiento;
import naranco.dam.proyectoalojamientos.respositories.AbstractRepository;
import naranco.dam.proyectoalojamientos.respositories.AlojamientoRepository;

import java.util.Set;

public class AlojamientoRepositoryImpl extends AbstractRepository implements AlojamientoRepository {
    @Override
    public void insertarAlojamiento(Set<Alojamiento> alojamientos) {
         ejecutar(entityManager -> {
             alojamientos.forEach((entityManager::persist));
              return true;
         });
    }
}
