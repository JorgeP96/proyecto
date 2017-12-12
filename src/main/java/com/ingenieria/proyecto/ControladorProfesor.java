package com.ingenieria.proyecto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class ControladorProfesor {
    @Autowired
    public RepositorioProfesor repoProfe;

    //Operaciones básicas
    //Obtener todos los profesores;
    @CrossOrigin
    @RequestMapping(value = "/profesor", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public ArrayList<Profesor> obtenerProfesores() {
        return (ArrayList<Profesor>) repoProfe.findAll();
    }

    //Obtener profesor por Id
    @CrossOrigin
    @RequestMapping(value = "/profesor/{id}", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public Profesor obtenerProfesorId(@PathVariable String id) throws Exception {
        return repoProfe.findOne(id);
    }

    //Guardar Profesor
    @CrossOrigin
    @RequestMapping(value = "/profesor", method = RequestMethod.POST, headers = {"Accept=application/json"})
    public void guardarProfesor(@RequestBody String datos) throws Exception {
        ObjectMapper maper = new ObjectMapper();
        Profesor profesor = maper.readValue(datos, Profesor.class);
        repoProfe.save(profesor);
    }

    //Actualizar profesor
    @CrossOrigin
    @RequestMapping(value = "/profesor", method = RequestMethod.PUT, headers = {"Accept=application/json"})
    public void actualizarProfesor(@RequestBody String datos) throws Exception {
        ObjectMapper maper = new ObjectMapper();
        Profesor profesor = maper.readValue(datos, Profesor.class);
        repoProfe.save(profesor);
    }

    //Eliminar profesor
    @CrossOrigin
    @RequestMapping(value = "/profesor/{id}", method = RequestMethod.DELETE, headers = {"Accept=application/json"})
    public void borrarProfesor(@PathVariable String id) throws Exception {
        repoProfe.delete(id);
    }
}
