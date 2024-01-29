package naranco.dam.proyectoalojamientos.serviceImpl;

import jakarta.transaction.Transactional;
import naranco.dam.proyectoalojamientos.model.Barrio;
import naranco.dam.proyectoalojamientos.respository.BarrioRespository;
import naranco.dam.proyectoalojamientos.respository.DistritoRespository;
import naranco.dam.proyectoalojamientos.service.BarrioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional

public class BarrioServiceImpl  implements BarrioService {

    @Autowired
    private BarrioRespository barrioRespository;

    @Autowired
    private DistritoRespository distritoRespository;
    @Override
    public void insertar(Set<Barrio> barrios) {
        barrioRespository.saveAll(barrios);
    }

    @Override
    public Long obtenerIDBarrioPorNombre(String nombre) {
        return barrioRespository.findByNombre(nombre);
    }
}
