package naranco.dam.proyectoalojamientos.serviceImpl;

import jakarta.transaction.Transactional;
import naranco.dam.proyectoalojamientos.model.Alojamiento;
import naranco.dam.proyectoalojamientos.respository.AlojamientoRepository;
import naranco.dam.proyectoalojamientos.service.AlojamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@Transactional
public class AlojamientoServiceImpl implements AlojamientoService {

    @Autowired
    AlojamientoRepository alojamientoRepository;

    @Override
    public List<Alojamiento> getAlojamientos() {
        return alojamientoRepository.findAll();
    }

    @Override
    public void insertar(Set<Alojamiento> alojamientos) {
        alojamientoRepository.saveAll(alojamientos);
    }
}
