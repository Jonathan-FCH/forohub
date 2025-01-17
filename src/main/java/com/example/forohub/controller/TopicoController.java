package com.example.forohub.controller;

import com.example.forohub.domain.topicos.*;
import com.example.forohub.domain.usuarios.DatosRegistroUsuario;
import com.example.forohub.domain.usuarios.Usuario;
import com.example.forohub.domain.usuarios.UsuarioRepository;
import com.example.forohub.topico.Topico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/topicos")
@Transactional
public class TopicoController {

    @Autowired
    private TopicosRepository topicosRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicos>> listarTopicos(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(
                topicosRepository.findAllTopicos(paginacion).map(DatosListadoTopicos::new));
    }

    @GetMapping("/mostrar/{id}")
    public ResponseEntity<Topico> mostrarTopicoPorID(@PathVariable Long id) {
        var topicoOptional = topicosRepository.findByIdAndStatusTrue(id);
        return ResponseEntity.ok(topicoOptional.get());
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> agregarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var usuarioActual = (Usuario) authentication.getPrincipal();

        Topico topico2 = new Topico(datosRegistroTopico.titulo(),datosRegistroTopico.mensaje(), LocalDate.now(),datosRegistroTopico.status(),usuarioActual,datosRegistroTopico.curso());

        Topico topico = topicosRepository.save(topico2);
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion(), topico.getStatus(),new DatosRegistroUsuario(usuarioActual), topico.getCurso());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @DeleteMapping("/desactivar/{id}")
    public ResponseEntity desactivarTopico(@PathVariable Long id){
        var topico = topicosRepository.getReferenceById(id);
        topico.setStatus(false);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity activarTopico(@PathVariable Long id){
        var topico = topicosRepository.getReferenceById(id);
        topico.setStatus(true);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity actualizarTopico(@RequestBody DatosActualizarTopico datos){

        var topico = topicosRepository.findById(datos.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));

        if (datos.titulo() != null) {
            topico.setTitulo(datos.titulo());
        }
        if (datos.mensaje() != null) {
            topico.setMensaje(datos.mensaje());
        }
        if (datos.status() != null) {
            topico.setStatus(datos.status());
        }
        if (datos.curso() != null) {
            topico.setCurso(datos.curso());
        }
        topicosRepository.save(topico);
        return ResponseEntity.ok(topico);
    }


}


