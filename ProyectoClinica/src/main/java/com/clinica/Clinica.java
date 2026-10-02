package com.clinica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Clinica {

    private List<Doctor> doctores;
    private List<Paciente> pacientes;
    private List<Cita> citas;

    private final String DB_FOLDER = "db";

    private final String DOCTORES_FILE =
            DB_FOLDER + File.separator + "doctores.dat";

    private final String PACIENTES_FILE =
            DB_FOLDER + File.separator + "pacientes.dat";

    private final String CITAS_FILE =
            DB_FOLDER + File.separator + "citas.dat";

    public Clinica() {

        crearCarpetaDB();

        doctores = cargarDatos(DOCTORES_FILE);
        pacientes = cargarDatos(PACIENTES_FILE);
        citas = cargarDatos(CITAS_FILE);

        if (doctores == null) {
            doctores = new ArrayList<>();
        }

        if (pacientes == null) {
            pacientes = new ArrayList<>();
        }

        if (citas == null) {
            citas = new ArrayList<>();
        }
    }

    private void crearCarpetaDB() {

        File carpeta = new File(DB_FOLDER);

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> cargarDatos(String archivo) {

        File file = new File(archivo);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream entrada =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            return (List<T>) entrada.readObject();

        } catch (IOException | ClassNotFoundException e) {

            System.out.println(
                    "No se pudieron cargar los datos de: " + archivo
            );

            return new ArrayList<>();
        }
    }

    private void guardarDatos(Object datos, String archivo) {

        try (ObjectOutputStream salida =
                     new ObjectOutputStream(
                             new FileOutputStream(archivo))) {

            salida.writeObject(datos);

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar los datos: "
                            + e.getMessage()
            );
        }
    }

    public boolean agregarDoctor(Doctor doctor) {

        if (buscarDoctor(doctor.getId()) != null) {
            return false;
        }

        doctores.add(doctor);

        guardarDatos(doctores, DOCTORES_FILE);

        return true;
    }

    public boolean agregarPaciente(Paciente paciente) {

        if (buscarPaciente(paciente.getId()) != null) {
            return false;
        }

        pacientes.add(paciente);

        guardarDatos(pacientes, PACIENTES_FILE);

        return true;
    }

    public boolean agregarCita(Cita cita) {

        if (buscarCita(cita.getId()) != null) {
            return false;
        }

        citas.add(cita);

        guardarDatos(citas, CITAS_FILE);

        return true;
    }

    public Doctor buscarDoctor(String id) {

        for (Doctor doctor : doctores) {

            if (doctor.getId().equalsIgnoreCase(id)) {
                return doctor;
            }
        }

        return null;
    }

    public Paciente buscarPaciente(String id) {

        for (Paciente paciente : pacientes) {

            if (paciente.getId().equalsIgnoreCase(id)) {
                return paciente;
            }
        }

        return null;
    }

    public Cita buscarCita(String id) {

        for (Cita cita : citas) {

            if (cita.getId().equalsIgnoreCase(id)) {
                return cita;
            }
        }

        return null;
    }

    public List<Doctor> getDoctores() {
        return doctores;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Cita> getCitas() {
        return citas;
    }
}