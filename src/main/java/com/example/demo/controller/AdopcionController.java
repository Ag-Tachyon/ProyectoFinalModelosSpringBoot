package com.example.demo.controller;

import com.example.demo.Facade.Adopcion;
import com.example.demo.bridge.*;
import com.example.demo.model.Mascota;
import com.example.demo.model.Refugio;
import com.example.demo.model.Usuario;
import com.example.demo.service.RefugioService;
import com.example.demo.singleton.UsuarioData;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adopciones")
@CrossOrigin(origins = "*")
public class AdopcionController {

    @Autowired
    private RefugioService refugioService;

    private final UsuarioData usuarioData = UsuarioData.getAdministrador();

    @PostMapping("/adoptar")
    public ResponseEntity<?> adoptarMascota(
            @RequestParam String mascotaNombre,
            @RequestParam String usuarioId) {

        System.out.println("🚀 Iniciando proceso de adopción");
        System.out.println("📝 Usuario: " + usuarioId + ", Mascota: " + mascotaNombre);

        try {
            // 1. Buscar la mascota en todos los refugios
            Mascota mascota = buscarMascotaPorNombre(mascotaNombre);
            if (mascota == null) {
                return ResponseEntity.badRequest().body("❌ Mascota no encontrada: " + mascotaNombre);
            }

            // 2. Verificar que la mascota no esté ya adoptada
            if (mascota.isAdoptado()) {
                return ResponseEntity.badRequest().body("❌ La mascota " + mascotaNombre + " ya ha sido adoptada");
            }

            // 3. Buscar el usuario
            Usuario usuario = buscarUsuarioPorNombre(usuarioId);
            if (usuario == null) {
                return ResponseEntity.badRequest().body("❌ Usuario no encontrado: " + usuarioId);
            }

            // 4. Cambiar estado de la mascota a adoptada y guardar en refugio
            boolean mascotaActualizada = actualizarMascotaEnRefugio(mascota);
            if (!mascotaActualizada) {
                return ResponseEntity.badRequest().body("❌ No se pudo actualizar la mascota en el refugio");
            }

            // 5. Generar documentos usando Facade + Bridge patterns
            System.out.println("📄 Generando documentos de adopción...");
            Adopcion adopcionFacade = new Adopcion();

            // Elegir formato de documentos (PDF o HTML)
            Exporter exporter = new PDFExporter(); // Cambia a HTMLExporter si quieres HTML

            Map<String, byte[]> documentos = adopcionFacade.generarDocumentos(mascota, usuario, exporter);

            System.out.println("✅ Documentos generados: " + documentos.size());

            // 6. Agregar mascota a la lista de mascotas del usuario
            usuario.agregarMascotaAdoptada(mascota);

            // Guardar cambios en usuario
            guardarCambiosUsuario(usuario);

            // 7. Enviar notificación de adopción exitosa
            System.out.println("✅ Adopción completada exitosamente para " + usuarioId);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"documentos_adopcion.zip\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new ByteArrayResource(comprimirDocumentos(documentos)));

        } catch (Exception e) {
            System.err.println("❌ Error en adopción: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Error en el proceso de adopción: " + e.getMessage());
        }
    }

    // Método para buscar y actualizar mascota en refugio
    private boolean actualizarMascotaEnRefugio(Mascota mascotaParaAdoptar) {
        List<Refugio> refugios = refugioService.obtenerTodos();

        for (Refugio refugio : refugios) {
            if (refugio.getMascotas() != null) {
                // Buscar la mascota por nombre en este refugio
                for (int i = 0; i < refugio.getMascotas().size(); i++) {
                    Mascota mascotaEnRefugio = refugio.getMascotas().get(i);

                    // Comparar por nombre (y opcionalmente por raza para mayor precisión)
                    if (mascotaEnRefugio.getNombre().equals(mascotaParaAdoptar.getNombre())) {

                        // Verificar que no esté ya adoptada
                        if (mascotaEnRefugio.isAdoptado()) {
                            System.out.println("⚠️ La mascota ya está adoptada: " + mascotaEnRefugio.getNombre());
                            return false;
                        }

                        // Actualizar estado a adoptado
                        mascotaEnRefugio.setAdoptado(true);
                        System.out.println("✅ Estado cambiado a adoptado para: " + mascotaEnRefugio.getNombre());

                        // Guardar cambios en el refugio
                        try {
                            // Reemplazar la mascota en la lista
                            refugio.getMascotas().set(i, mascotaEnRefugio);

                            // Guardar el refugio completo
                            refugioService.agregarMascotaARefugio(refugio.getNombre(), mascotaEnRefugio);
                            System.out.println("✅ Refugio actualizado: " + refugio.getNombre());

                            // Actualizar la referencia para usar después
                            mascotaParaAdoptar.setAdoptado(true);

                            return true;
                        } catch (Exception e) {
                            System.err.println("❌ Error al actualizar refugio: " + e.getMessage());
                            return false;
                        }
                    }
                }
            }
        }

        System.out.println("❌ Mascota no encontrada en ningún refugio: " + mascotaParaAdoptar.getNombre());
        return false;
    }

    // Método para guardar cambios en usuario
    private void guardarCambiosUsuario(Usuario usuarioActualizado) {
        List<Usuario> usuarios = usuarioData.leerDatos();
        boolean encontrado = false;

        // Buscar y reemplazar el usuario
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombreUsuario().equals(usuarioActualizado.getNombreUsuario())) {
                usuarios.set(i, usuarioActualizado);
                encontrado = true;
                break;
            }
        }

        // Si no se encontró, agregarlo
        if (!encontrado) {
            usuarios.add(usuarioActualizado);
        }

        usuarioData.guardarDatos(usuarios);
        System.out.println("✅ Usuario actualizado en base de datos: " + usuarioActualizado.getNombreUsuario());
    }

    // Método auxiliar para buscar mascota por nombre
    private Mascota buscarMascotaPorNombre(String nombre) {
        List<Refugio> refugios = refugioService.obtenerTodos();

        for (Refugio refugio : refugios) {
            if (refugio.getMascotas() != null) {
                for (Mascota mascota : refugio.getMascotas()) {
                    if (mascota.getNombre().equalsIgnoreCase(nombre)) {
                        System.out.println("✅ Mascota encontrada: " + mascota.getNombre() + " en refugio: " + refugio.getNombre());
                        return mascota;
                    }
                }
            }
        }
        System.out.println("❌ Mascota no encontrada: " + nombre);
        return null;
    }

    // Método auxiliar para buscar usuario por nombre
    private Usuario buscarUsuarioPorNombre(String nombre) {
        List<Usuario> usuarios = usuarioData.leerDatos();
        Usuario usuario = usuarios.stream()
                .filter(u -> u.getNombreUsuario().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);

        if (usuario != null) {
            System.out.println("✅ Usuario encontrado: " + usuario.getNombreUsuario());
        } else {
            System.out.println("❌ Usuario no encontrado: " + nombre);
        }
        return usuario;
    }

    // Método para comprimir documentos en un ZIP
    private byte[] comprimirDocumentos(Map<String, byte[]> documentos) throws Exception {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        try (java.util.zip.ZipOutputStream zos = new java.util.zip.ZipOutputStream(baos)) {

            // Agregar certificado
            if (documentos.containsKey("certificado")) {
                java.util.zip.ZipEntry zipEntry = new java.util.zip.ZipEntry("certificado_adopcion.pdf");
                zos.putNextEntry(zipEntry);
                zos.write(documentos.get("certificado"));
                zos.closeEntry();
            }

            // Agregar ficha médica
            if (documentos.containsKey("ficha")) {
                java.util.zip.ZipEntry zipEntry = new java.util.zip.ZipEntry("ficha_medica.pdf");
                zos.putNextEntry(zipEntry);
                zos.write(documentos.get("ficha"));
                zos.closeEntry();
            }
        }
        System.out.println("✅ Documentos comprimidos en ZIP");
        return baos.toByteArray();
    }

    // Endpoint para debug - ver todos los refugios y mascotas
    @GetMapping("/debug/refugios")
    public ResponseEntity<?> debugRefugios() {
        List<Refugio> refugios = refugioService.obtenerTodos();
        StringBuilder debugInfo = new StringBuilder();

        debugInfo.append("=== DEBUG DE REFUGIOS ===\n");
        debugInfo.append("Total refugios: ").append(refugios.size()).append("\n\n");

        for (Refugio refugio : refugios) {
            debugInfo.append("Refugio: ").append(refugio.getNombre())
                    .append(" | Mascotas: ").append(refugio.getMascotas() != null ? refugio.getMascotas().size() : 0)
                    .append("\n");

            if (refugio.getMascotas() != null) {
                for (Mascota mascota : refugio.getMascotas()) {
                    debugInfo.append("  - ").append(mascota.getNombre())
                            .append(" (Raza: ").append(mascota.getRaza())
                            .append(", Adoptado: ").append(mascota.isAdoptado())
                            .append(")\n");
                }
            }
            debugInfo.append("\n");
        }

        return ResponseEntity.ok(debugInfo.toString());
    }

    // Endpoint para debug - ver todos los usuarios
    @GetMapping("/debug/usuarios")
    public ResponseEntity<?> debugUsuarios() {
        List<Usuario> usuarios = usuarioData.leerDatos();
        StringBuilder debugInfo = new StringBuilder();

        debugInfo.append("=== DEBUG DE USUARIOS ===\n");
        debugInfo.append("Total usuarios: ").append(usuarios.size()).append("\n\n");

        for (Usuario usuario : usuarios) {
            debugInfo.append("Usuario: ").append(usuario.getNombreUsuario())
                    .append(" | Mascotas adoptadas: ").append(usuario.getMascotas() != null ? usuario.getMascotas().size() : 0)
                    .append("\n");
        }

        return ResponseEntity.ok(debugInfo.toString());
    }
}