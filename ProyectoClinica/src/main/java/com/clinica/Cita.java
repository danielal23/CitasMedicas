package com.clinica;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(String id,
                LocalDate fecha,
                LocalTime hora,
                String motivo,
                Doctor doctor,
                Paciente paciente) {

        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public String getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    @Override
    public String toString() {
        return "ID cita: " + id +
                " | Fecha: " + fecha +
                " | Hora: " + hora +
                " | Motivo: " + motivo +
                " | Doctor: " + doctor.getNombre() +
                " | Paciente: " + paciente.getNombre();
    }
}