package naranco.dam.proyectoalojamientos;

import naranco.dam.proyectoalojamientos.model.*;
import naranco.dam.proyectoalojamientos.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class AppStartupRunner implements ApplicationListener<ApplicationReadyEvent> {


    @Autowired
    private Environment environment;

    // ------------------- SERVICIOS -------------------
    @Autowired
    private DistritoService distritoService;
    @Autowired
    private BarrioService barrioService;
    @Autowired
    private PropietarioService propietarioService;
    @Autowired
    private TipoHabitacionService tipoHabitacionService;
    @Autowired
    private AlojamientoService alojamientoService;


    //-------------------  DATOS  -------------------
    private Set<Alojamiento> coleccionAlojamientos = new LinkedHashSet<Alojamiento>();
    private Set<Barrio> coleccionDeBarrios = new LinkedHashSet<Barrio>();
    private Set<Distrito> coleccionDeDistritos = new LinkedHashSet<Distrito>();
    private Set<Propietario> coleccionDePropietarios = new LinkedHashSet<Propietario>();
    private Set<TipoHabitacion> coleccionTiposDeHabitacion = new LinkedHashSet<TipoHabitacion>();

    /**
     * Este método se ejecuta después de que se arranque la aplicación.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        boolean runDataLoad = Boolean.parseBoolean(environment.getProperty("app.data.run"));
        if (runDataLoad) {
            cargarDatosDeFichero();
            insercionEnBaseDeDatos();
        }

    }

    private void insercionEnBaseDeDatos() {
        distritoService.insertar(coleccionDeDistritos);
        actualizarLosDistritosBarrios();
        barrioService.insertar(coleccionDeBarrios);
        tipoHabitacionService.insertar(coleccionTiposDeHabitacion);
        propietarioService.insertar(coleccionDePropietarios);
        actualizarLosAlojamientos();
        alojamientoService.insertar(coleccionAlojamientos);
        System.out.println("Inserción hecha");
    }

    private void actualizarLosAlojamientos() {
        coleccionAlojamientos.forEach(alojamiento -> {
            alojamiento.getBarrio().setId(barrioService.obtenerIDBarrioPorNombre(alojamiento.getBarrio().getNombre()));
            alojamiento.getTipoHabitacion().setId(tipoHabitacionService.obtenerIDTipoHabitacionPorNombre(alojamiento.getTipoHabitacion().getNombre()));
        });
    }

    private void actualizarLosDistritosBarrios() {
        coleccionDeBarrios.forEach(barrio -> {
            barrio.getDistrito().setId(distritoService.obtenerIdDistritoPorNombre(barrio.getDistrito().getNombre()));
        });
    }

    private void cargarDatosDeFichero() {
        int i = 0;
        try (BufferedReader br = new BufferedReader(
                new FileReader(environment.getProperty("app.data.filename")))) {
            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                Long idAlojamiento = Long.parseLong(campos[0]);
                String datosAlojamiento = campos[1];
                Long idPropietario = Long.parseLong(campos[2]);
                String nombrePropietario = campos[3];
                String nombreDistrito = campos[4];
                String nombreBarrio = campos[5];
                double latitud = Double.parseDouble(campos[6]);
                double longitud = Double.parseDouble(campos[7]);
                String tipoDeHabitacion = campos[8];
                double precio = Double.parseDouble(campos[9]);
                double puntuacion = gestionarPuntuacion(campos);
                int habitaciones = gestionarHabitaciones(campos);
                int camas = Integer.parseInt(campos[12]);
                int[] cuartosDeAseo = gestionarBanosYAseos(campos);
                int banos = cuartosDeAseo[0];
                int aseos = cuartosDeAseo[1];

                Distrito distrito = new Distrito(nombreDistrito);
                Barrio barrio = new Barrio(nombreBarrio, distrito);
                gestionarDistritos(distrito, barrio);
                TipoHabitacion tipoHabitacion = new TipoHabitacion(tipoDeHabitacion);
                Propietario propietario = new Propietario(idPropietario, nombrePropietario);

                Alojamiento alojamiento = new Alojamiento(idAlojamiento, aseos, banos, camas, habitaciones, latitud, longitud, precio, puntuacion, datosAlojamiento);

                alojamiento.setBarrio(barrio);
                alojamiento.setTipoHabitacion(tipoHabitacion);
                alojamiento.setPropietario(propietario);

                this.coleccionAlojamientos.add(alojamiento);

                gestionarBarrios(barrio, alojamiento);
                gestionarTipoHabitacion(tipoHabitacion, alojamiento);
                gestionarPropietario(propietario, alojamiento);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void gestionarDistritos(Distrito distrito, Barrio barrio) {
        boolean existe = this.coleccionDeDistritos.add(distrito);
        if (!existe) {
            Optional<Distrito> distritoOptional = this.coleccionDeDistritos.stream().filter((distritoEnLista -> {
                return distritoEnLista.equals(distrito);
            })).findFirst();
            distritoOptional.ifPresent(distritoEnLista -> distritoEnLista.getBarrios().add(barrio));
        }
    }

    private void gestionarPropietario(Propietario propietario, Alojamiento alojamiento) {
       // propietario.getAlojamientos().add(alojamiento);
        boolean existe = this.coleccionDePropietarios.add(propietario);
 /*       if (!existe) {
            Optional<Propietario> propietarioOptional = this.coleccionDePropietarios.stream().filter((propietarioEnLista -> {
                return propietarioEnLista.equals(propietarioEnLista);
            })).findFirst();
            propietarioOptional.ifPresent(propietarioEnLista -> propietarioEnLista.getAlojamientos().add(alojamiento));
        }*/
    }

    private void gestionarTipoHabitacion(TipoHabitacion tipoHabitacion, Alojamiento alojamiento) {
        tipoHabitacion.getAlojamientos().add(alojamiento);
        boolean existe = this.coleccionTiposDeHabitacion.add(tipoHabitacion);
        if (!existe) {
            Optional<TipoHabitacion> tipoOptional = this.coleccionTiposDeHabitacion.stream().filter((tipoEnLista -> {
                return tipoEnLista.equals(tipoHabitacion);
            })).findFirst();
            tipoOptional.ifPresent(tipoEnLista -> tipoEnLista.getAlojamientos().add(alojamiento));
        }
    }

    private void gestionarBarrios(Barrio barrio, Alojamiento alojamiento) {
        barrio.getAlojamientos().add(alojamiento);
        boolean existe = this.coleccionDeBarrios.add(barrio);
        if (!existe) {
            Optional<Barrio> barrioOptional = this.coleccionDeBarrios.stream().filter((barrioEnLista -> {
                return barrioEnLista.equals(barrio);
            })).findFirst();
            barrioOptional.ifPresent(barrioEnLista -> barrioEnLista.getAlojamientos().add(alojamiento));
        }
    }

    private static int[] gestionarBanosYAseos(String[] campos) {
        int aseos = 0;
        int banos;
        double numBanos;
        int[] cuartosDeAseo = new int[2];
        try {
            numBanos = Double.parseDouble(campos[13]);
            banos = (int) Math.floor(numBanos);
            if (numBanos - banos > 0) {
                aseos = 1;
            }
        } catch (NumberFormatException e) {
            banos = 0;
        }
        cuartosDeAseo[0] = banos;
        cuartosDeAseo[1] = aseos;
        return cuartosDeAseo;
    }

    private static int gestionarHabitaciones(String[] campos) {
        int habitaciones;
        try {
            habitaciones = Integer.parseInt(campos[11]);
        } catch (NumberFormatException e) {
            habitaciones = 0;
        }
        return habitaciones;
    }

    private static double gestionarPuntuacion(String[] campos) {
        double puntuacion;
        try {
            puntuacion = Double.parseDouble(campos[10]);
        } catch (NumberFormatException e) {
            puntuacion = 0;
        }
        return puntuacion;
    }

}