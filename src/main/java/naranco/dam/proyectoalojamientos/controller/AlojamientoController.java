package naranco.dam.proyectoalojamientos.controller;


import naranco.dam.proyectoalojamientos.model.Alojamiento;
import naranco.dam.proyectoalojamientos.model.Barrio;
import naranco.dam.proyectoalojamientos.model.Propietario;
import naranco.dam.proyectoalojamientos.model.TipoHabitacion;
import naranco.dam.proyectoalojamientos.service.AlojamientoService;
import naranco.dam.proyectoalojamientos.service.BarrioService;
import naranco.dam.proyectoalojamientos.service.PropietarioService;
import naranco.dam.proyectoalojamientos.service.TipoHabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "alojamiento/")
public class AlojamientoController extends AbstractController{

    @Autowired
    private AlojamientoService alojamientoService;

    //Otros servicios:
    @Autowired
    private PropietarioService propietarioService;
    @Autowired
    private BarrioService barrioService;
    @Autowired
    private TipoHabitacionService tipoHabitacionService;

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
    public ResponseEntity<Alojamiento> addAlojamiento(@RequestBody Alojamiento alojamiento, @RequestHeader String authorization
    ){
        comprobarAutorizacion(authorization);
        Optional<Alojamiento> alojamientoOptional = alojamientoService.findById(alojamiento.getId()); //buscamos a ver si dicho alojamiento existe
        if(alojamientoOptional.isEmpty()){ //si NO existe lo añadimos...
            comprobarNulos(alojamiento);
            Propietario propietario = obtenerPropietario(alojamiento.getPropietario().getId());
            TipoHabitacion tipoHabitacion = obtenerTipoHabitacion(alojamiento.getTipoHabitacion().getId());
            Barrio barrio = obtenerBarrio(alojamiento.getBarrio().getId());
            alojamiento.setPropietario(propietario);
            alojamiento.setBarrio(barrio);
            alojamiento.setTipoHabitacion(tipoHabitacion);
            return new ResponseEntity<Alojamiento>(alojamientoService.saveAlojamiento(alojamiento), HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID ya existe. Ya existe un alojamiento con este ID");
        }
    }

    @PutMapping()
    public ResponseEntity<Alojamiento> updateAlojamiento(@RequestBody Alojamiento alojamiento, @RequestHeader String authorization
    ){
        comprobarAutorizacion(authorization);
        Optional<Alojamiento> updateAlojamiento = alojamientoService.findById(alojamiento.getId()); //buscamos a ver si dicho alojamiento existe
        if(updateAlojamiento.isPresent()){ //si existe lo actualizamos
            comprobarNulos(alojamiento);
             obtenerPropietario(alojamiento.getPropietario().getId());
             obtenerTipoHabitacion(alojamiento.getTipoHabitacion().getId());
            obtenerBarrio(alojamiento.getBarrio().getId());
            return new ResponseEntity<Alojamiento>(this.alojamientoService.updateAlojamiento(alojamiento), HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de este alojamiento no existe. Introduce un ID de alojamiento válido");
        }
    }

    private static void comprobarNulos(Alojamiento alojamiento) {
        if(alojamiento.getPropietario()==null || alojamiento.getBarrio()==null || alojamiento.getTipoHabitacion()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debes de instroducir un ID de propietaria, de barrio y de tipo de habitación válido");
        }
    }


    private Barrio obtenerBarrio(Long id) {
        Optional<Barrio> barrioOptional =  this.barrioService.findById(id);
        if(barrioOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID del barrio NO existe");
        }else{
            return barrioOptional.get();
        }
    }

    private TipoHabitacion obtenerTipoHabitacion(Long id) {
        Optional<TipoHabitacion> tipoHabitacionOptional =  this.tipoHabitacionService.findById(id);
        if(tipoHabitacionOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID del tipo de habitación NO existe");
        }else{
            return tipoHabitacionOptional.get();
        }
    }

    private Propietario obtenerPropietario(Long id) {
       Optional<Propietario> propietarioOptional =  this.propietarioService.findById(id);
       if(propietarioOptional.isEmpty()){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID del propietario NO existe");
       }else{
           return propietarioOptional.get();
       }
    }

}
