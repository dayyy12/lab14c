// Archivo: DemoApplicationTests.java

package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Asume que la clase principal se llama Main y está en el mismo paquete
import com.example.demo.Main; 


// Corregido: Especificar la clase principal para que Spring Boot pueda encontrarla.
@SpringBootTest(classes = Main.class) 
class DemoApplicationTests {

    @Test
    void contextLoads() {
        // Esta prueba verifica que el contexto de Spring Boot se carga correctamente
    }
}
