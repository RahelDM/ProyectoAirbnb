package naranco.dam.proyectoalojamientos.respositories;

import naranco.dam.proyectoalojamientos.model.Alojamiento;

import java.util.Set;

public interface AlojamientoRepository {

    void insertarAlojamiento(Set<Alojamiento> alojamiento);

}
