package com.example.forohub.domain.topicos;

import com.example.forohub.domain.usuarios.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosRegistroTopico(
        @NotNull
        @NotBlank
        String      titulo,
        @NotNull
        @NotBlank
        String      mensaje,
        @NotNull
        Boolean     status,
        @NotNull
        @NotBlank
        String      curso)


{
}
