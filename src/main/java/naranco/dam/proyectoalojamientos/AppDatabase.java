package naranco.dam.proyectoalojamientos;

import naranco.dam.proyectoalojamientos.model.*;
import naranco.dam.proyectoalojamientos.respositories.*;
import naranco.dam.proyectoalojamientos.respositories.respositoriesImpl.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class AppDatabase {

    //LISTA DE DATOS
    private Set<Alojamiento> coleccionAlojamientos = new LinkedHashSet<Alojamiento>();
    private Set<Barrio> coleccionDeBarrios = new LinkedHashSet<Barrio>();
    private Set<Distrito> coleccionDeDistritos = new LinkedHashSet<Distrito>();
    private Set<Propietario> coleccionDePropietarios = new LinkedHashSet<Propietario>();
    private Set<TipoHabitacion> coleccionTiposDeHabitacion = new LinkedHashSet<TipoHabitacion>();


    //REPOSITORIOS
    private AlojamientoRepository alojamientoRepository = new AlojamientoRepositoryImpl();
    private BarrioRespository barrioRespository = new BarrioRespositoryImpl();
    private DistritoRespository distritoRespository = new DistritoRespositoryImpl();
    private PropietarioRespository propietarioRespository = new PropietarioRespositoryImpl();
    private TipoHabitacionRespository tipoHabitacionRespository = new TipoHabitacionRespositoryImpl();


    public AppDatabase(Set<Alojamiento> coleccionAlojamientos, Set<Barrio> coleccionDeBarrios,
                       Set<Distrito> coleccionDeDistritos, Set<Propietario> coleccionDePropietarios,
                       Set<TipoHabitacion> coleccionTiposDeHabitacion) {
        this.coleccionAlojamientos = coleccionAlojamientos;
        this.coleccionDeBarrios = coleccionDeBarrios;
        this.coleccionDeDistritos = coleccionDeDistritos;
        this.coleccionDePropietarios = coleccionDePropietarios;
        this.coleccionTiposDeHabitacion = coleccionTiposDeHabitacion;
    }

    public void insercionEnBaseDeDatos() {

        propietarioRespository.insertarPropietario(coleccionDePropietarios);
        tipoHabitacionRespository.insertarTipoHabitacion(coleccionTiposDeHabitacion);
        alojamientoRepository.insertarAlojamiento(coleccionAlojamientos);

    }


}
