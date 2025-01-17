package com.example.forohub.domain.usuarios;

import com.example.forohub.domain.topicos.DatosRegistroTopico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosRegistroUsuario(

        Long id,
        @NotNull
        @NotBlank
        String usuario,
        @NotNull
        @NotBlank
        String clave)
{

    public  DatosRegistroUsuario(Usuario usuario){
        this( usuario.getId(), usuario.getUsuario(), usuario.getClave());

    }



}

