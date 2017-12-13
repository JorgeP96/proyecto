package com.ingenieria.proyecto;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;

public interface RepositorioProfesor extends MongoRepository<Profesor, String> {

    @Query("{\"curso.idCurso\": \"1\"}")
    Profesor find1();

    @Query("{\"curso.idCurso\": \"2\"}")
    Profesor find2();

    @Query("{\"curso.idCurso\": \"3\"}")
    Profesor find3();

    @Query("{\"curso.idCurso\": \"4\"}")
    Profesor find4();

    @Query("{\"curso.idCurso\": \"5\"}")
    Profesor find5();

    @Query("{\"curso.idCurso\": \"6\"}")
    Profesor find6();

    @Query("{\"curso.idCurso\": \"7\"}")
    Profesor find7();

    @Query("{\"curso.idCurso\": \"8\"}")
    Profesor find8();

    @Query("{\"curso.idCurso\": \"9\"}")
    Profesor find9();

    @Query("{\"curso.idCurso\": \"10\"}")
    Profesor find10();
}
