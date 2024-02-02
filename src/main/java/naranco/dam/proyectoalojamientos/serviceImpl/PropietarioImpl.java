package naranco.dam.proyectoalojamientos.serviceImpl;

import jakarta.transaction.Transactional;
import naranco.dam.proyectoalojamientos.model.Propietario;
import naranco.dam.proyectoalojamientos.respository.PropietarioRespository;
import naranco.dam.proyectoalojamientos.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class PropietarioImpl implements PropietarioService {

    @Autowired
    PropietarioRespository propietarioRespository;
    @Override
    public void insertar(Set<Propietario> propietarios) {
        for(Propietario propietario:propietarios){
            Optional<Propietario>propietarioOptional= propietarioRespository.findById(propietario.getId());
            if(!propietarioOptional.isPresent()){ // propietarioOptional.isEmpty()
                propietarioRespository.save(propietario);
            }
        }
    }

    @Override
    public Optional<Propietario> findById(Long id) {
        return this.propietarioRespository.findById(id);
    }

}
