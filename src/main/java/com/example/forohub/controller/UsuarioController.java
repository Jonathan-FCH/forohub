package com.example.forohub.controller;

import com.example.forohub.domain.usuarios.DatosRegistroUsuario;
import com.example.forohub.domain.usuarios.Usuario;
import com.example.forohub.domain.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/registrar")
@Transactional
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyectar el PasswordEncoder

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
        // Verificar si el nombre de usuario ya existe en la base de datos
        Optional<Usuario> usuarioExistente = usuarioRepository.findByUsuario(datosRegistroUsuario.usuario());

        if (usuarioExistente.isPresent()) {
            // Si el usuario ya existe, devolver un mensaje de error
            return ResponseEntity.status(400).body("El nombre de usuario ya está registrado");
        }

        // Cifrar la contraseña antes de guardar el usuario
        String claveCifrada = passwordEncoder.encode(datosRegistroUsuario.clave());

        // Crear un nuevo usuario con la contraseña cifrada
        Usuario usuario = new Usuario(null, datosRegistroUsuario.usuario(), claveCifrada);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario creado con éxito");
    }
}