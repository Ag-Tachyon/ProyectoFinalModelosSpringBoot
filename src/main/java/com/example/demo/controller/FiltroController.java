package com.example.demo.controller;

import com.example.demo.iterator.IteradorMascotaConcreto;
import com.example.demo.model.Mascota;
import com.example.demo.model.Refugio;
import com.example.demo.strategy.FiltroEdad;
import com.example.demo.strategy.FiltroSize;
import com.example.demo.strategy.InterfazFiltroMascotas;
import com.example.demo.service.RefugioService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/filtros")
@CrossOrigin(origins = "*")
public class FiltroController {

    @Autowired
    private RefugioService refugioService;

    @PostMapping("/aplicar")
    public List<Mascota> aplicarFiltros(@RequestBody FiltroRequest filtros) {
        System.out.println("🎯 Aplicando filtros - Tamaño: " + filtros.getSize() + ", Edad mínima: " + filtros.getEdadMinima());

        // Obtener todas las mascotas de todos los refugios
        List<Mascota> todasMascotas = new ArrayList<>();
        List<Refugio> refugios = refugioService.obtenerTodos();

        for (Refugio refugio : refugios) {
            if (refugio.getMascotas() != null) {
                todasMascotas.addAll(refugio.getMascotas());
            }
        }

        System.out.println("📊 Total mascotas: " + todasMascotas.size());

        // Crear lista de filtros (Solo tamaño y edad como dijiste)
        List<InterfazFiltroMascotas> filtrosList = new ArrayList<>();

        // Solo filtro de tamaño si se especificó
        if (filtros.getSize() != null && !filtros.getSize().isEmpty()) {
            filtrosList.add(new FiltroSize(filtros.getSize()));
        }

        // Solo filtro de edad si se especificó
        if (filtros.getEdadMinima() > 0) {
            filtrosList.add(new FiltroEdad(filtros.getEdadMinima()));
        }

        // Si no hay filtros, retornar todas
        if (filtrosList.isEmpty()) {
            return todasMascotas;
        }

        // Crear filtro combinado (AND entre los filtros)
        InterfazFiltroMascotas filtroCombinado = new InterfazFiltroMascotas() {
            @Override
            public boolean cumple(Mascota mascota) {
                for (InterfazFiltroMascotas filtro : filtrosList) {
                    if (!filtro.cumple(mascota)) {
                        return false;
                    }
                }
                return true;
            }
        };

        // Usar Iterator para filtrar
        List<Mascota> resultado = new ArrayList<>();
        IteradorMascotaConcreto iterador = new IteradorMascotaConcreto(
                new ArrayList<>(todasMascotas),
                filtroCombinado,
                0
        );

        while (iterador.hasNext()) {
            Mascota mascota = iterador.next();
            if (mascota != null) {
                resultado.add(mascota);
            }
        }

        System.out.println("✅ Mascotas encontradas: " + resultado.size());
        return resultado;
    }
}