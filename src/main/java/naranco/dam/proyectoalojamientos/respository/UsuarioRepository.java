package naranco.dam.proyectoalojamientos.respository;


import naranco.dam.proyectoalojamientos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByNombreAndPassword(String nombre, String contrasena);
}