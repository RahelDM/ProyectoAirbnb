package naranco.dam.proyectoalojamientos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.FileReader;

@SpringBootApplication
public class ProyectoAlojamientosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoAlojamientosApplication.class, args);
    }



}
