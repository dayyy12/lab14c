package com.example.demo;

public class Main {
    public static void main(String[] args) {
        UserRegistrationService service = new UserRegistrationService();

        boolean ok;

        ok = service.registerUser("juan", "123", "juan@correo"); // contraseña corta
        System.out.println("Registro juan ok? " + ok + " -> " + service.getLastErrorMessage());

        ok = service.registerUser(null, "12345678", "correo-sin-arroba"); // username null
        System.out.println("Registro null ok? " + ok + " -> " + service.getLastErrorMessage());

        ok = service.registerUser("error", "12345678", "error@correo.com"); // fuerza excepción
        System.out.println("Registro error ok? " + ok + " -> " + service.getLastErrorMessage());

        ok = service.registerUser("maria", "password123", "maria@example.com");
        System.out.println("Registro maria ok? " + ok + " -> " + service.getLastErrorMessage());
    }
}