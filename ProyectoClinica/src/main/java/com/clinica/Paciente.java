package com.clinica;

import java.io.Serializable;

public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nombre;

    public Paciente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Nombre: " + nombre;
    }
}