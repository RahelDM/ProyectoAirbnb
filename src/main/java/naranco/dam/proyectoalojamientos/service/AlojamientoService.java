package naranco.dam.proyectoalojamientos.service;

import naranco.dam.proyectoalojamientos.model.Alojamiento;
import naranco.dam.proyectoalojamientos.model.Distrito;

import java.util.List;
import java.util.Set;

public interface AlojamientoService {

    List<Alojamiento> getAlojamientos();
    void insertar(Set<Alojamiento> alojamientos);

}
