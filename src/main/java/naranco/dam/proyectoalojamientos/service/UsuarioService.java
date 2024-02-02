package naranco.dam.proyectoalojamientos.service;


import naranco.dam.proyectoalojamientos.model.Usuario;

public interface UsuarioService {
    Usuario findByNombreAndPassword(String nombre, String password);
}
