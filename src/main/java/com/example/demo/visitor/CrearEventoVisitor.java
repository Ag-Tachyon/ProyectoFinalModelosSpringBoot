package com.example.demo.visitor;

import com.example.demo.model.RefugioGatos;
import com.example.demo.model.RefugioPerros;

public class CrearEventoVisitor implements  RefugioVisitor {


    @Override
    public String visitar(RefugioPerros refugioPerros) {
        return "Evento de prueba para (perros)"+ refugioPerros.toString();
    }

    @Override
    public String visitar(RefugioGatos refugioGatos) {
        return "Evento de prueba para (gatos)"+ refugioGatos.toString();
    }
}
