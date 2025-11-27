package com.example.demo.Facade;

import com.example.demo.model.Mascota;
import com.example.demo.model.Usuario;

public class FichaMedica {

    private Mascota mascota;
    private Usuario veterinario,cliente;

    private StringBuilder info;
    public FichaMedica(Mascota mascota, Usuario veterinario) {
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.cliente = cliente;
    }
    public void generar(){
        info = new StringBuilder("Historial medico de tu mascota: \n" +
                mascota.getTarjetaVacunas() +
                "\n veterinario y propietario "+
                veterinario.getNombreUsuario()+" y "
                +cliente.getNombreUsuario());
        //llamar al metodo encargadod e generara el documento que lo exporta como pdf 
    }


}
