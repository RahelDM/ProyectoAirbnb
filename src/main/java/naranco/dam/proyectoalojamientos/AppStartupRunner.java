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

        try (BufferedReader br = new BufferedReader(
                new FileReader(environment.getProperty("app.data.filename")))) {
            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                //Lectura del fichero
                System.out.println("ndjsf");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}