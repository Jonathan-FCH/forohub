package com.example.forohub.topico;

import com.example.forohub.domain.topicos.DatosListadoTopicos;
import com.example.forohub.domain.topicos.DatosRegistroTopico;
import com.example.forohub.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


//
@Entity(name = "Topico")
//
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String titulo;
    private String mensaje;
    private Boolean status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor")
    private Usuario autor;
    private String curso;
    private LocalDate fechaDeCreacion;

    public Topico(String titulo, String mensaje, LocalDate fechaDeCreacion, boolean status, Usuario autor, String curso){
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaDeCreacion = fechaDeCreacion;
        this.status = status;
        this.autor = autor;
        this.curso = curso;
    }

//    public void setStatusFalse (){
//        this.status = false;
//    }
//    public void setStatusTrue (){
//        this.status = true;
//    }


    public void setStatus (boolean estado){

        this.status = estado;

    }

    /////

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }



}
