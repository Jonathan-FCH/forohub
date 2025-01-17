package com.example.forohub.domain.topicos;

import com.example.forohub.domain.usuarios.DatosRegistroUsuario;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DatosRespuestaTopico(

        Long id,
        String      titulo,
        String      mensaje,
        LocalDate fecha_de_creacion,
        Boolean     status,
        DatosRegistroUsuario autor ,
        String      curso
) {
}
