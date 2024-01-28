package naranco.dam.proyectoalojamientos.respositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.function.Function;

public class AbstractRepository {

    private EntityManagerFactory factory;

    public AbstractRepository(){
        factory = Persistence.createEntityManagerFactory("alojamientos");
    }

    public <R> R ejecutar(Function<EntityManager, R> function) {
        final EntityManager entityManager = factory.createEntityManager();
        try {
            return function.apply(entityManager);
        } finally {
            entityManager.close();
        }
    }

    public <R> R ejecutarEnTransaccion(Function<EntityManager, R> function) {
        return ejecutar(entityManager -> {
            entityManager.getTransaction().begin();

            final R result = function.apply(entityManager);

            entityManager.getTransaction().commit();

            return result;
        });
    }
}
