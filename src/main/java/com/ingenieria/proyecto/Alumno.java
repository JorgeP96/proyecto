package com.ingenieria.proyecto;

public class Alumno extends Persona {
    public Alumno() {
    }

    public Alumno(String id) {
        super(id);
    }

    public Alumno(String nombre, String tipo, String departamento, Curso curso) {
        super(nombre, tipo, departamento, curso);
    }

    public Alumno(String id, String nombre, String tipo, String departamento, Curso curso) {
        super(id, nombre, tipo, departamento, curso);
    }
}
