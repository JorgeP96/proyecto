package com.ingenieria.proyecto;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioCurso extends MongoRepository<Curso, String> {
}
