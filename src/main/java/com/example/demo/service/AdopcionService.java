package com.example.demo.service;

import com.example.demo.model.Mascota;
import com.example.demo.model.Usuario;
import com.example.demo.repository.MascotaRepository;
import org.springframework.stereotype.Service;

@Service
public class AdopcionService {

    // Asumiendo que tienes repositorios para acceder a los datos
    private final MascotaRepository mascotaRepository;
    private final UsuarioRepository usuarioRepository;

    public AdopcionService(MascotaRepository mascotaRepository, UsuarioRepository usuarioRepository) {
        this.mascotaRepository = mascotaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Lógica de negocio para procesar la adopción.
     * @param mascotaId ID de la mascota a adoptar.
     * @param usuarioId ID del usuario que adopta.
     * @return Mascota adoptada (con estado actualizado).
     */
    public Mascota procesarAdopcion(String mascotaId, String usuarioId) {

        // 1. Obtener la Mascota y el Usuario
        Mascota mascota = mascotaRepository.findById(mascotaId);
        Usuario usuario = usuarioRepository.findById(usuarioId);

        if (mascota == null || usuario == null) {
            throw new RuntimeException("Mascota o Usuario no encontrado.");
        }

        // 2. Ejecutar el cambio de estado (Patrón State)
        // La mascota cambia su estado interno si el estado actual lo permite.
        mascota.adoptarMascota(mascota);

        // 3. Registrar la mascota en la lista del usuario (Lógica de negocio)
        usuario.agregarMascotaAdoptada(mascota);

        // 4. Guardar los cambios
        mascotaRepository.save(mascota);
        usuarioRepository.save(usuario);

        return mascota;
    }
}