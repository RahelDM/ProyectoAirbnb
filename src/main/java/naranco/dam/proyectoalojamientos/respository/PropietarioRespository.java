package naranco.dam.proyectoalojamientos.respository;

import naranco.dam.proyectoalojamientos.model.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface PropietarioRespository   extends JpaRepository<Propietario,Long> {


}
