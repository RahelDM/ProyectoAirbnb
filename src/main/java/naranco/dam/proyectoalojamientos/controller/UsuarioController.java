package naranco.dam.proyectoalojamientos.controller;


import naranco.dam.proyectoalojamientos.model.Usuario;
import naranco.dam.proyectoalojamientos.service.UsuarioService;
import naranco.dam.proyectoalojamientos.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JWTUtil jwtUtil;

    //Usamos el post porque estamos enviando información
    @PostMapping("/usuario/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuarioRequest){
        Usuario usuario = usuarioService.findByNombreAndPassword(usuarioRequest.getNombre(),usuarioRequest.getPassword());
        if (usuario!=null){
            String token = jwtUtil.create(String.valueOf(usuario.getId()), usuario.getNombre());
            return ResponseEntity.ok(token);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
