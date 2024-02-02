package naranco.dam.proyectoalojamientos.serviceImpl;

import jakarta.transaction.Transactional;
import naranco.dam.proyectoalojamientos.model.TipoHabitacion;
import naranco.dam.proyectoalojamientos.respository.TipoHabitacionRespository;
import naranco.dam.proyectoalojamientos.service.TipoHabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class TipoHabitacionImpl implements TipoHabitacionService {

    @Autowired
    private TipoHabitacionRespository tipoHabitacionRespository;
    @Override
    public void insertar(Set<TipoHabitacion> tiposHabitacion) {
        for(TipoHabitacion tipoHabitacion:tiposHabitacion){
            Long idHabitacion = tipoHabitacionRespository.findByNombre(tipoHabitacion.getNombre());
            if(idHabitacion==null){
                tipoHabitacionRespository.save(tipoHabitacion);
            }
        }
    }

    @Override
    public Long obtenerIDTipoHabitacionPorNombre(String nombre) {
        return tipoHabitacionRespository.findByNombre(nombre);
    }

    @Override
    public Optional<TipoHabitacion> findById(Long id) {
        return this.tipoHabitacionRespository.findById(id);
    }
}
