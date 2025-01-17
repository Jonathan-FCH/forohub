package com.example.forohub.domain.topicos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        Boolean status,
        String curso
) {
}
