package naranco.dam.proyectoalojamientos.respositories.respositoriesImpl;

import naranco.dam.proyectoalojamientos.model.Distrito;
import naranco.dam.proyectoalojamientos.respositories.AbstractRepository;
import naranco.dam.proyectoalojamientos.respositories.DistritoRespository;

import java.util.Set;

public class DistritoRespositoryImpl extends AbstractRepository implements DistritoRespository {
    @Override
    public void insertarDistrito(Set<Distrito> distritos) {
        ejecutarEnTransaccion(entityManager -> {
            distritos.forEach((entityManager::persist));
            return true;
        });
    }


}
