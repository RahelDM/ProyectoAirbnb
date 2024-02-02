package naranco.dam.proyectoalojamientos.controller;


import naranco.dam.proyectoalojamientos.model.Alojamiento;
import naranco.dam.proyectoalojamientos.service.AlojamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "alojamiento/")
public class AlojamientoController extends AbstractController{

    @Autowired
    private AlojamientoService alojamientoService;

    @GetMapping
    public List<Alojamiento> getAlojamientos(){
       return alojamientoService.getAlojamientos();
    }

    @GetMapping("distrito/{id}")
    public List<Alojamiento> getAlojamientosByDistrito(@PathVariable Long id){
        return alojamientoService.getAlojamientosByDistrito(id);
    }
    @GetMapping("distrito/{id}/min/{min}/max/{max}")
    public List<Alojamiento> getAlojamientosByDistritoMinYMax(@PathVariable Long id, @PathVariable double min, @PathVariable double max){
        return alojamientoService.getAlojamientosByDistritoMinYMax(id,min,max);
    }

    @GetMapping("distrito/{id}/habitaciones/{numHabitacion}")
    public List<Alojamiento> getAlojamientoByDistritoHabitaciones(@PathVariable Long id, @PathVariable int numHabitacion){
        return alojamientoService.getAlojamientoByDistritoHabitaciones(id,numHabitacion);
    }

    @GetMapping("distrito/{id}/habitaciones/{numHabitacion}/banos/{banos}")
    public List<Alojamiento> getAlojamientosByDistritoMinYMax(@PathVariable Long id, @PathVariable int numHabitacion, @PathVariable int banos){
        return alojamientoService.getAlojamientoByDistritoHabitacionesYBanos(id,numHabitacion,banos);
    }

    @GetMapping("distrito/{id}/minCalificacion/{minCalificacion}/maxCalificacion/{maxCalificacion}")
    public List<Alojamiento> getAlojamientoByDistritoCalificacion(@PathVariable Long id,  @PathVariable double minCalificacion,@PathVariable double maxCalificacion){
        return alojamientoService.getAlojamientoByDistritoCalificacion(id,minCalificacion,maxCalificacion);
    }

    @GetMapping("distrito/{id}/min/{minCompleta}/max/{maxCompleta}/minCalificacion/{minCalificacionCompleta}/maxCalificacion/{maxCalificacionCompleta}")
    public List<Alojamiento> getAlojamientoByDistritoCompleto(@PathVariable Long id,@PathVariable double minCompleta,
                                                              @PathVariable double maxCompleta,  @PathVariable double minCalificacionCompleta,@PathVariable double maxCalificacionCompleta){
        return alojamientoService.getAlojamientoByDistritoCompleto(id,minCompleta,maxCompleta,minCalificacionCompleta,maxCalificacionCompleta);
    }

    @PostMapping()
    public ResponseEntity<Alojamiento> addAlojamiento(@RequestBody Alojamiento alojamiento
    ,@RequestHeader String authorization){
        comprobarAutorizacion(authorization);
        Optional<Alojamiento> alojamientoOptional = alojamientoService.findAlojamientoById(alojamiento.getId());
        if(alojamientoOptional.isPresent()){
            return new ResponseEntity<Alojamiento>(alojamientoService.saveAlojamiento(alojamiento), HttpStatus.OK);
        }
        return new ResponseEntity<Alojamiento>(HttpStatus.NO_CONTENT);
    }

}
