package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Servicio de registro de usuarios (versión corregida).
 * Esta clase es final para evitar la herencia insegura.
 */
public final class UserRegistrationService {

    /** Almacena el último mensaje de error generado. */
    private String lastErrorMessage = "";

    /** Lista de nombres de usuario registrados. Es final. */
    private final List<String> users = new ArrayList<>();

    /** Longitud mínima requerida para la contraseña. Es final y estática. */
    private static final int MIN_PASSWORD_LENGTH = 8;

    /** Patrón simple para validar la estructura básica de un correo electrónico. */
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$"
    );

    /**
     * Constructor por defecto del servicio.
     */
    public UserRegistrationService() {
        // Constructor limpio.
    }

    /**
     * Retorna el último mensaje de error generado por una operación fallida.
     * @return El mensaje de error.
     */
    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    /**
     * Registra un nuevo usuario aplicando varias reglas de validación.
     *
     * @param username El nombre de usuario a registrar (no debe ser null ni vacío).
     * @param password La contraseña del usuario (mínimo 8 caracteres).
     * @param email El correo electrónico del usuario (debe ser válido).
     * @return true si se registra correctamente, false si hay un error
     * (y {@code lastErrorMessage} se setea).
     */
    public boolean registerUser(
            final String username, final String password, final String email) {
        // Validaciones de entrada (null safe)
        if (username == null || username.trim().isEmpty()) {
            lastErrorMessage = "El nombre de usuario está vacío o es null.";
            return false;
        }
        if (password == null) {
            lastErrorMessage = "La contraseña es null.";
            return false;
        }
        if (password.length() < MIN_PASSWORD_LENGTH) {
            lastErrorMessage = "La contraseña es muy corta.";
            return false;
        }
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            lastErrorMessage = "El correo electrónico no parece válido.";
            return false;
        }

        // Evitar duplicados y manejar concurrencia
        synchronized (users) {
            if (users.contains(username)) {
                lastErrorMessage = "Usuario ya existe.";
                return false;
            }
            try {
                saveUser(username, password, email);
            } catch (final IllegalArgumentException e) {
                lastErrorMessage = "Error al guardar el usuario: " 
                        + e.getMessage();
                return false;
            } catch (final Exception e) {
                // Registramos el error real (mejor que ocultarlo)
                lastErrorMessage = "Error desconocido al guardar el usuario: " 
                        + e.getMessage();
                return false;
            }
        }

        lastErrorMessage = "";
        return true;
    }

    /**
     * Simula el proceso de guardado de usuario.
     * Lanza una excepción si el nombre de usuario es "error".
     *
     * @param username Nombre de usuario.
     * @param password Contraseña (no utilizada).
     * @param email Correo electrónico (no utilizado).
     */
    private void saveUser(
            final String username, final String password, final String email) {
        // Guardado simple en lista
        if ("error".equals(username)) {
            throw new IllegalArgumentException("Nombre de usuario no permitido.");
        }
        users.add(username);
    }

    /**
     * Calcula la longitud de una cadena, retornando -1 si es null.
     * @param s La cadena a medir.
     * @return La longitud de la cadena, o -1 si es null.
     */
    public int lengthOfString(final String s) {
        if (s == null) {
            return -1;
        }
        return s.length();
    }
}
