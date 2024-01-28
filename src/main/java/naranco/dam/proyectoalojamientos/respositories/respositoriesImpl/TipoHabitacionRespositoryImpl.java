package naranco.dam.proyectoalojamientos.respositories.respositoriesImpl;

import naranco.dam.proyectoalojamientos.model.TipoHabitacion;
import naranco.dam.proyectoalojamientos.respositories.AbstractRepository;
import naranco.dam.proyectoalojamientos.respositories.TipoHabitacionRespository;

import java.util.Set;

public class TipoHabitacionRespositoryImpl extends AbstractRepository implements TipoHabitacionRespository {
    @Override
    public void insertarTipoHabitacion(Set<TipoHabitacion> tiposHabitacion) {
        ejecutar(entityManager -> {
            tiposHabitacion.forEach((entityManager::persist));
            return true;
        });
    }
}
