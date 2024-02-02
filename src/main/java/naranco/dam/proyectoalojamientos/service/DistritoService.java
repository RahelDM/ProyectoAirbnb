package naranco.dam.proyectoalojamientos.service;

import naranco.dam.proyectoalojamientos.model.Distrito;

import java.util.List;
import java.util.Set;

public interface DistritoService {

    void insertar(Set<Distrito> distritos);
    Long obtenerIdDistritoPorNombre(String nombreDistrito);
    List<Distrito> getDistritos();
}
