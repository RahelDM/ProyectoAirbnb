package naranco.dam.proyectoalojamientos;

import jakarta.persistence.EntityManagerFactory;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class DatabaseFactory {
    private EntityManagerFactory factory;
    private static DatabaseFactory instance;

    private DatabaseFactory() {
        factory = createEntityManagerFactory("alojamientos");
    }

    public static DatabaseFactory getInstance() {
        if (instance == null) {
            instance = new DatabaseFactory();
        }
        return instance;
    }
}
