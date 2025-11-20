package com.example.demo;

/**
 * Clase principal que demuestra el uso del servicio de registro de usuarios.
 * Es una clase de utilidad que no debe ser instanciada.
 */
public final class Main {
    
    // Constructor privado para evitar la instanciación de la clase de utilidad.
    private Main() {
        // La clase es solo para métodos estáticos.
    }

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(final String[] args) {
        UserRegistrationService service = new UserRegistrationService();

        boolean ok;

        // Caso 1: Contraseña corta (Línea dividida para cumplir con el límite de 80)
        ok = service.registerUser("juan", "123", "juan@correo"); 
        System.out.println("Registro juan ok? " + ok + " -> " + service.getLastErrorMessage());

        // Caso 2: Username null
        ok = service.registerUser(null, "12345678", "correo-sin-arroba"); 
        System.out.println("Registro null ok? " + ok + " -> " + service.getLastErrorMessage());

        // Caso 3: Fuerza excepción
        ok = service.registerUser("error", "12345678", "error@correo.com"); 
        System.out.println("Registro error ok? " + ok + " -> " + service.getLastErrorMessage());

        // Caso 4: Registro exitoso
        ok = service.registerUser("maria", "password123", "maria@example.com");
        System.out.println("Registro maria ok? " + ok + " -> " + service.getLastErrorMessage());
    }
}
