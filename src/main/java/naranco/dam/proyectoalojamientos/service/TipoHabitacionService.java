package naranco.dam.proyectoalojamientos.service;

import naranco.dam.proyectoalojamientos.model.TipoHabitacion;

import java.util.Set;

public interface TipoHabitacionService {

    void insertar(Set<TipoHabitacion> tiposHabitacion);

    Long obtenerIDTipoHabitacionPorNombre(String nombre);


}
