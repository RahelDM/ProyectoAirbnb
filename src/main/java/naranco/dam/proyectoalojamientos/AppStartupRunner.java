package naranco.dam.proyectoalojamientos;

import naranco.dam.proyectoalojamientos.model.*;
import naranco.dam.proyectoalojamientos.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Component
public class AppStartupRunner implements ApplicationListener<ApplicationReadyEvent> {


    @Autowired
    private Environment environment;


    /**
     * Este método se ejecuta después de que se arranque la aplicación.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        boolean runDataLoad = Boolean.parseBoolean(environment.getProperty("app.data.run"));

        if (runDataLoad) {
            cargarDatosDeFichero();
        }
    }

    private void cargarDatosDeFichero() {
        int i=0;
        try (BufferedReader br = new BufferedReader(
                new FileReader(environment.getProperty("app.data.filename")))) {
            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                Long idAlojamiento = Long.parseLong(campos[0]);
                String datosAlojamiento = campos[1];
                Long idPropietario = Long.parseLong(campos[2]);
                String nombrePropietario = campos[3];
                String nombreBarrio = campos[4];
                String nombreDistrito = campos[5];
                double latitud = Double.parseDouble(campos[6]);
                double longitud = Double.parseDouble(campos[7]);
                String nombreHabitacion = campos[8];
                double precio =  Double.parseDouble(campos[9]);

                double puntuacion;
                try {
                     puntuacion =  Double.parseDouble(campos[10]);
                }catch (NumberFormatException e){
                    puntuacion=0;
                }

                int habitaciones;
                try {
                    habitaciones = Integer.parseInt(campos[11]);

                }catch (NumberFormatException e){

                }

                int camas = Integer.parseInt(campos[12]);

                int banos;
                try {
                     banos = Integer.parseInt(campos[13]);
                }catch (NumberFormatException e){
                    banos=0;
                }


                System.out.println(i++);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}