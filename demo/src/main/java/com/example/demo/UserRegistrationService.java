package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Servicio de registro de usuarios (versión corregida).
 */
public class UserRegistrationService {

    // ahora privado con getter
    private String lastErrorMessage = "";

    // usar genéricos y almacenar un objeto simple (aquí String username por simplicidad)
    private final List<String> users = new ArrayList<>();

    private static final int MIN_PASSWORD_LENGTH = 8;

    // patrón simple para validar email (mejorable según requisitos)
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$"
    );

    public UserRegistrationService() {
        // constructor limpio (sin prints)
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    /**
     * Registra un nuevo usuario.
     * @return true si se registra correctamente, false si hay error (y lastErrorMessage se setea)
     */
    public boolean registerUser(String username, String password, String email) {
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

        // Evitar duplicados
        synchronized (users) {
            if (users.contains(username)) {
                lastErrorMessage = "Usuario ya existe.";
                return false;
            }
            try {
                saveUser(username, password, email);
            } catch (IllegalArgumentException e) {
                lastErrorMessage = "Error al guardar el usuario: " + e.getMessage();
                return false;
            } catch (Exception e) {
                // registramos el error real (mejor que ocultarlo)
                lastErrorMessage = "Error desconocido al guardar el usuario: " + e.getMessage();
                return false;
            }
        }

        lastErrorMessage = "";
        return true;
    }

    private void saveUser(String username, String password, String email) {
        // Guardado simple en lista (en vez de lanzar Exception genérica)
        if ("error".equals(username)) {
            throw new IllegalArgumentException("Nombre de usuario no permitido.");
        }
        users.add(username);
    }

    // renombrado y optimizado
    public int lengthOfString(String s) {
        if (s == null) {
            return -1;
        }
        // usar StringBuilder o simplemente s.length()
        return s.length();
    }
}