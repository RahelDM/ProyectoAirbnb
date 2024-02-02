package naranco.dam.proyectoalojamientos.service;


import naranco.dam.proyectoalojamientos.model.Propietario;

import java.util.Optional;
import java.util.Set;

public interface PropietarioService {


    void insertar(Set<Propietario> propietarios);
    Optional<Propietario> findById(Long id);
}
