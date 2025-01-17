package com.example.forohub.domain.usuarios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosAutenticacionUsuario(
        @NotNull
        @NotBlank
        String usuario,
        @NotNull
        @NotBlank
        String clave) {
}
