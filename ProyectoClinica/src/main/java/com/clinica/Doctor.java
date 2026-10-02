package com.clinica;

import java.io.Serializable;

public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nombre;
    private String especialidad;

    public Doctor(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Nombre: " + nombre +
                " | Especialidad: " + especialidad;
    }
}
