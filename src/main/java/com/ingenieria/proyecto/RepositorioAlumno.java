package com.ingenieria.proyecto;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface RepositorioAlumno extends MongoRepository<Alumno, String> {
    @Query("{\"curso.idCurso\": \"1\"}")
    List<Alumno> find1();

    @Query("{\"curso.idCurso\": \"2\"}")
    List<Alumno> find2();
}
