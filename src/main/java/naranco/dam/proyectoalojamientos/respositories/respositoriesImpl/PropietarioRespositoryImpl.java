package naranco.dam.proyectoalojamientos.respositories.respositoriesImpl;

import naranco.dam.proyectoalojamientos.model.Propietario;
import naranco.dam.proyectoalojamientos.respositories.AbstractRepository;
import naranco.dam.proyectoalojamientos.respositories.PropietarioRespository;

import java.util.Set;

public class PropietarioRespositoryImpl extends AbstractRepository implements PropietarioRespository {
    @Override
    public void insertarPropietario(Set<Propietario> propietarios) {
        ejecutar(entityManager -> {
            propietarios.forEach((entityManager::persist));
            return true;
        });
    }
}
