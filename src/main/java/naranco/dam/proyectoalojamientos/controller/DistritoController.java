package naranco.dam.proyectoalojamientos.controller;


import naranco.dam.proyectoalojamientos.model.Distrito;
import naranco.dam.proyectoalojamientos.service.DistritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "distrito/")
public class DistritoController {

    @Autowired
    private DistritoService distritoService;


    @GetMapping
    public List<Distrito> getDistritos(){
        return distritoService.getDistritos();
    }
}
