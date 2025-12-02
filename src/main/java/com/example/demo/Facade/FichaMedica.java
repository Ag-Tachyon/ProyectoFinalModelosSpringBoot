package com.example.demo.Facade;

import com.example.demo.model.Mascota;
import com.example.demo.model.Usuario;
import com.example.demo.bridge.GenerarDocumento;
import com.example.demo.bridge.Exporter;

public class FichaMedica extends GenerarDocumento {

    private Mascota mascota;
    private Usuario veterinario,cliente;
    private StringBuilder info;

    public FichaMedica(Mascota mascota, Usuario veterinario , Exporter exporter) {
        super(exporter);
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.cliente = cliente;
    }

    public void infMascota(Mascota mascota) {
        this.mascota = mascota;

        this.titulo = "Ficha Médica";
        this.contenido =
                "Mascota: " + mascota.getNombre() +
                        "\nEdad: " + mascota.getEdad() +
                        "\nRaza: " + mascota.getRaza();
    }

    @Override
    public void mostrar() {
        System.out.println("=== " + titulo + " ===");
        System.out.println(contenido);
    }

    @Override
    protected void construirContenido() {
        info = new StringBuilder("Historial medico de tu mascota: \n" +
                mascota.getTarjetaVacunas() +
                "\n veterinario y propietario "+
                veterinario.getNombreUsuario()+" y "
                +cliente.getNombreUsuario());
    }
}
