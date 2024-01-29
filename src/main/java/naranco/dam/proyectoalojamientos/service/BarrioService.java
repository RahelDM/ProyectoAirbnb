package naranco.dam.proyectoalojamientos.service;

import naranco.dam.proyectoalojamientos.model.Barrio;
import naranco.dam.proyectoalojamientos.model.Distrito;

import java.util.Set;

public interface BarrioService {

    void insertar(Set<Barrio> barrios);

    Long obtenerIDBarrioPorNombre(String nombre);
}
