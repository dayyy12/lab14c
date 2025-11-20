// Archivo: DemoApplicationTests.java

package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

// Nota: Hemos eliminado todas las importaciones y referencias a Spring Boot Test.

class DemoApplicationTests {

    // Prueba simple que verifica que la lógica de registro básica funciona
    @Test
    void userRegistrationSuccess() {
        UserRegistrationService service = new UserRegistrationService();
        boolean result = service.registerUser("nuevoUsuario", "password123", "user@example.com");
        
        // Assert: Esperamos que el registro sea exitoso
        assertTrue(result, "El registro debería ser exitoso con datos válidos.");
    }
    
    // Prueba para verificar un fallo de validación
    @Test
    void userRegistrationFailsOnShortPassword() {
        UserRegistrationService service = new UserRegistrationService();
        // Contraseña corta
        boolean result = service.registerUser("juan", "123", "juan@correo.com"); 
        
        // Assert: Esperamos que el registro falle
        assertFalse(result, "El registro debería fallar con una contraseña corta.");
    }

}
