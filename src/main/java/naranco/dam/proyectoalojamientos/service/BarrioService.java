package naranco.dam.proyectoalojamientos.service;

import naranco.dam.proyectoalojamientos.model.Barrio;

import java.util.Optional;
import java.util.Set;

public interface BarrioService {

    void insertar(Set<Barrio> barrios);

    Long obtenerIDBarrioPorNombre(String nombre);

    Optional<Barrio> findById(Long id);
}
