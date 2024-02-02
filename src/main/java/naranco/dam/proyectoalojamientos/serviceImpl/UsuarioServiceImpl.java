package naranco.dam.proyectoalojamientos.serviceImpl;


import naranco.dam.proyectoalojamientos.model.Usuario;
import naranco.dam.proyectoalojamientos.respository.UsuarioRepository;
import naranco.dam.proyectoalojamientos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public Usuario findByNombreAndPassword(String nombre, String password){
        return usuarioRepository.findByNombreAndPassword(nombre,password);
    }

}
