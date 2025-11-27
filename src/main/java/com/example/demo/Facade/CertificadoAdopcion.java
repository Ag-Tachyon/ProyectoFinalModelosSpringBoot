package com.example.demo.Facade;

import com.example.demo.model.Mascota;
import com.example.demo.model.Usuario;

public class CertificadoAdopcion {

    private Mascota mascota;
    private Usuario veterinario,cliente;

    private StringBuilder info;
    public CertificadoAdopcion(Mascota mascota, Usuario veterinario) {
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.cliente = cliente;
    }
    public void generar(){
        info = new StringBuilder("Datos de la mascota \n" +
                mascota.mostrarInfo()+
                "veterinario y propietario "+ veterinario.getNombreUsuario()+" y "
                +cliente.getNombreUsuario());
    }
    //llamar a generara documento posterior

}
