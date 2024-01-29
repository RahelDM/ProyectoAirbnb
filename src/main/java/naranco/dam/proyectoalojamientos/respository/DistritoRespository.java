package naranco.dam.proyectoalojamientos.respository;

import naranco.dam.proyectoalojamientos.model.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DistritoRespository extends JpaRepository<Distrito,Long> {

    @Query("SELECT d.id FROM distritos d WHERE d.nombre=?1")
    Long findByNombre(String nombreDistrito);
}
