package com.example.forohub.domain.topicos;

import com.example.forohub.domain.usuarios.DatosRegistroUsuario;
import com.example.forohub.topico.Topico;

import java.time.LocalDate;

public record DatosListadoTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaDeCreacion,
        Boolean status,
        DatosRegistroUsuario autor,
        String curso) {

    public DatosListadoTopicos(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
               new DatosRegistroUsuario( topico.getAutor()),
                topico.getCurso());
    }
}


