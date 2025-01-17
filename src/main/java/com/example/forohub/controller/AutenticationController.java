package com.example.forohub.controller;

import com.example.forohub.domain.topicos.DatosRegistroTopico;
import com.example.forohub.domain.topicos.DatosRespuestaTopico;
import com.example.forohub.domain.usuarios.DatosAutenticacionUsuario;
import com.example.forohub.domain.usuarios.DatosRegistroUsuario;
import com.example.forohub.domain.usuarios.Usuario;
import com.example.forohub.infra.security.DatosJWTToken;
import com.example.forohub.infra.security.TokenService;
import com.example.forohub.topico.Topico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;


@RestController
@RequestMapping("/login")
@Transactional
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;




    @PostMapping
    public ResponseEntity pruebaLogin(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {


        try {

            Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.usuario(),
                    datosAutenticacionUsuario.clave());
            var usuarioAutenticado = authenticationManager.authenticate(authToken);
            var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
            return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
        }
        catch(Exception e ){
            System.out.println(e);
        }
        return ResponseEntity.ok().build();

    }




}
