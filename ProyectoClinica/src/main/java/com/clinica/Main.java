package com.clinica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final DateTimeFormatter FORMATO_HORA =
            DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) {

        Clinica clinica = new Clinica();

        int opcion;

        do {

            mostrarMenu();

            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {

                case 1:
                    registrarDoctor(clinica);
                    break;

                case 2:
                    registrarPaciente(clinica);
                    break;

                case 3:
                    crearCita(clinica);
                    break;

                case 4:
                    mostrarDoctores(clinica);
                    break;

                case 5:
                    mostrarPacientes(clinica);
                    break;

                case 6:
                    mostrarCitas(clinica);
                    break;

                case 7:
                    System.out.println();
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println();
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 7);

        scanner.close();
    }

    private static void mostrarMenu() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("    SISTEMA DE CITAS MÉDICAS");
        System.out.println("======================================");
        System.out.println("1. Registrar doctor");
        System.out.println("2. Registrar paciente");
        System.out.println("3. Crear cita");
        System.out.println("4. Mostrar doctores");
        System.out.println("5. Mostrar pacientes");
        System.out.println("6. Mostrar citas");
        System.out.println("7. Salir");
        System.out.println("======================================");
    }

    private static void registrarDoctor(Clinica clinica) {

        System.out.println();
        System.out.println("----- REGISTRAR DOCTOR -----");

        String id = leerTexto("ID del doctor: ");
        String nombre = leerTexto("Nombre completo: ");
        String especialidad = leerTexto("Especialidad: ");

        Doctor doctor =
                new Doctor(id, nombre, especialidad);

        if (clinica.agregarDoctor(doctor)) {

            System.out.println();
            System.out.println(
                    "Doctor registrado correctamente."
            );

        } else {

            System.out.println();
            System.out.println(
                    "Error: ya existe un doctor con ese ID."
            );
        }
    }

    private static void registrarPaciente(Clinica clinica) {

        System.out.println();
        System.out.println("----- REGISTRAR PACIENTE -----");

        String id = leerTexto("ID del paciente: ");
        String nombre = leerTexto("Nombre completo: ");

        Paciente paciente =
                new Paciente(id, nombre);

        if (clinica.agregarPaciente(paciente)) {

            System.out.println();
            System.out.println(
                    "Paciente registrado correctamente."
            );

        } else {

            System.out.println();
            System.out.println(
                    "Error: ya existe un paciente con ese ID."
            );
        }
    }

    private static void crearCita(Clinica clinica) {

        System.out.println();
        System.out.println("----- CREAR CITA -----");

        if (clinica.getDoctores().isEmpty()) {

            System.out.println(
                    "Primero debe registrar al menos un doctor."
            );

            return;
        }

        if (clinica.getPacientes().isEmpty()) {

            System.out.println(
                    "Primero debe registrar al menos un paciente."
            );

            return;
        }

        String id = leerTexto("ID de la cita: ");

        if (clinica.buscarCita(id) != null) {

            System.out.println(
                    "Error: ya existe una cita con ese ID."
            );

            return;
        }

        String idDoctor =
                leerTexto("ID del doctor: ");

        Doctor doctor =
                clinica.buscarDoctor(idDoctor);

        if (doctor == null) {

            System.out.println(
                    "Error: el doctor no existe."
            );

            return;
        }

        String idPaciente =
                leerTexto("ID del paciente: ");

        Paciente paciente =
                clinica.buscarPaciente(idPaciente);

        if (paciente == null) {

            System.out.println(
                    "Error: el paciente no existe."
            );

            return;
        }

        LocalDate fecha = leerFecha();

        LocalTime hora = leerHora();

        String motivo =
                leerTexto("Motivo de la consulta: ");

        Cita cita = new Cita(
                id,
                fecha,
                hora,
                motivo,
                doctor,
                paciente
        );

        if (clinica.agregarCita(cita)) {

            System.out.println();
            System.out.println(
                    "Cita creada correctamente."
            );

        } else {

            System.out.println();
            System.out.println(
                    "No fue posible crear la cita."
            );
        }
    }

    private static void mostrarDoctores(Clinica clinica) {

        System.out.println();
        System.out.println("----- DOCTORES -----");

        if (clinica.getDoctores().isEmpty()) {

            System.out.println(
                    "No hay doctores registrados."
            );

            return;
        }

        for (Doctor doctor : clinica.getDoctores()) {
            System.out.println(doctor);
        }
    }

    private static void mostrarPacientes(Clinica clinica) {

        System.out.println();
        System.out.println("----- PACIENTES -----");

        if (clinica.getPacientes().isEmpty()) {

            System.out.println(
                    "No hay pacientes registrados."
            );

            return;
        }

        for (Paciente paciente : clinica.getPacientes()) {
            System.out.println(paciente);
        }
    }

    private static void mostrarCitas(Clinica clinica) {

        System.out.println();
        System.out.println("----- CITAS -----");

        if (clinica.getCitas().isEmpty()) {

            System.out.println(
                    "No hay citas registradas."
            );

            return;
        }

        for (Cita cita : clinica.getCitas()) {
            System.out.println(cita);
        }
    }

    private static String leerTexto(String mensaje) {

        while (true) {

            System.out.print(mensaje);

            String texto =
                    scanner.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println(
                    "El campo no puede estar vacío."
            );
        }
    }

    private static int leerEntero(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Ingrese un número válido."
                );
            }
        }
    }

    private static LocalDate leerFecha() {

        while (true) {

            try {

                System.out.print(
                        "Fecha (dd/MM/yyyy): "
                );

                return LocalDate.parse(
                        scanner.nextLine(),
                        FORMATO_FECHA
                );

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Fecha incorrecta. Ejemplo: 15/10/2026"
                );
            }
        }
    }

    private static LocalTime leerHora() {

        while (true) {

            try {

                System.out.print(
                        "Hora (HH:mm): "
                );

                return LocalTime.parse(
                        scanner.nextLine(),
                        FORMATO_HORA
                );

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Hora incorrecta. Ejemplo: 14:30"
                );
            }
        }
    }
}