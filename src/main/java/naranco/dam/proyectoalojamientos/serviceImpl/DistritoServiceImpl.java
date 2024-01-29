package naranco.dam.proyectoalojamientos.serviceImpl;

import jakarta.transaction.Transactional;
import naranco.dam.proyectoalojamientos.model.Distrito;
import naranco.dam.proyectoalojamientos.respository.DistritoRespository;
import naranco.dam.proyectoalojamientos.service.DistritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
@Transactional
public class DistritoServiceImpl implements DistritoService {

    @Autowired
    private DistritoRespository distritoRespository;

    @Override
    public void insertar(Set<Distrito> distritos) {
       distritoRespository.saveAll(distritos);
    }
    @Override
    public List<Distrito> getDistritos() {
        return distritoRespository.findAll();
    }
    @Override
    public Long obtenerIdDistritoPorNombre(String nombreDistrito) {
        Long distritoID = distritoRespository.findByNombre(nombreDistrito);
        return distritoID;
    }


}
