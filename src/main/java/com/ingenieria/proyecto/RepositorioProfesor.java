package com.ingenieria.proyecto;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.lang.reflect.Array;
import java.util.List;

public interface RepositorioProfesor extends MongoRepository<Profesor, String> {

}
