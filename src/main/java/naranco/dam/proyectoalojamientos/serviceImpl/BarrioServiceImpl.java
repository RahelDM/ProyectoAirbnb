package naranco.dam.proyectoalojamientos.serviceImpl;

import jakarta.transaction.Transactional;
import naranco.dam.proyectoalojamientos.model.Barrio;
import naranco.dam.proyectoalojamientos.respository.BarrioRespository;
import naranco.dam.proyectoalojamientos.service.BarrioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional

public class BarrioServiceImpl  implements BarrioService {

    @Autowired
    private BarrioRespository barrioRespository;

    @Override
    public void insertar(Set<Barrio> barrios) {
        for(Barrio barrio:barrios){
            Long idBarrio = barrioRespository.findByNombre(barrio.getNombre());
            if(idBarrio==null){
                barrioRespository.save(barrio);
            }
        }
    }

    @Override
    public Long obtenerIDBarrioPorNombre(String nombre) {
        return barrioRespository.findByNombre(nombre);
    }

    @Override
    public Optional<Barrio> findById(Long id) {
        return this.barrioRespository.findById(id);
    }
}
