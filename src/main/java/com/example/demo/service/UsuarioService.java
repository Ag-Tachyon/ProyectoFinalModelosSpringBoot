package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private static final String RUTA = "src/main/resources/usuarios.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public void guardarUsuario(Usuario usuario) {
        try {
            File file = new File(RUTA);

            List<Usuario> usuarios;

            // Si no existe el archivo, crea la lista vacía
            if (!file.exists()) {
                usuarios = new ArrayList<>();
            } else {
                usuarios = mapper.readValue(file, new TypeReference<List<Usuario>>() {});
            }

            usuarios.add(usuario);

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, usuarios);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
