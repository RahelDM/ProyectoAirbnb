package naranco.dam.proyectoalojamientos.respositories.respositoriesImpl;

import naranco.dam.proyectoalojamientos.model.Barrio;
import naranco.dam.proyectoalojamientos.respositories.AbstractRepository;
import naranco.dam.proyectoalojamientos.respositories.BarrioRespository;

import java.util.Set;

public class BarrioRespositoryImpl extends AbstractRepository implements BarrioRespository {
    @Override
    public void insertarBarrio(Set<Barrio> barrios) {
        ejecutar(entityManager -> {
            barrios.forEach((entityManager::persist));
            return true;
        });
    }
}
