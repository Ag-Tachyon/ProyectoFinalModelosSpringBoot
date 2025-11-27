package com.example.demo;

import com.example.demo.absFactory.FabricaRefugio;
import com.example.demo.absFactory.FabricaRefugioGatos;
import com.example.demo.absFactory.FabricaRefugioPerros;
import com.example.demo.model.Refugio;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalApplication {

    public static void main(String[] args) {
        // Ejecuta la aplicación Spring Boot
        SpringApplication.run(FinalApplication.class, args);

        // --- Lógica de prueba para la creación de refugios (Patrón Factory Abstracto) ---

        System.out.println("=================================================");
        System.out.println("         DEMOSTRACIÓN DEL PATRÓN FACTORY         ");
        System.out.println("=================================================");

        // 1. Crear la Fábrica de Gatos
        FabricaRefugio fabricaGatos = new FabricaRefugioGatos();

        // 2. Usar la fábrica para crear un Refugio de Gatos
        // El cliente solo ve la interfaz FabricaRefugio, pero recibe un Refugio.
        Refugio refugioFelino = fabricaGatos.crearRefugio("MiGatito", "Calle del Sol #123", 50);

        System.out.println("\nRefugio Creado (Gatos):");
        System.out.println(refugioFelino.mostrarInfoRefugio());
        //refugioFelino.registrarMascota();

        // 3. Crear la Fábrica de Perros
        FabricaRefugio fabricaPerros = new FabricaRefugioPerros();

        // 4. Usar la fábrica para crear un Refugio de Perros
        Refugio refugioCanino = fabricaPerros.crearRefugio("ElCanil", "Avenida de la Luna #456", 100);

        System.out.println("\nRefugio Creado (Perros):");
        System.out.println(refugioCanino.mostrarInfoRefugio());
        //refugioCanino.registrarMascota();

        System.out.println("=================================================");
        System.out.println("El código principal (main) está desacoplado.");
        System.out.println("=================================================");
    }
}