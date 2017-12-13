package com.ingenieria.proyecto;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface RepositorioAlumno extends MongoRepository<Alumno, String> {
    ArrayList<Alumno> findByCurso(Curso curso);
}
