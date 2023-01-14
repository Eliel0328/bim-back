package com.example.prueba.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba.dto.AuthCredentialRequest;
import com.example.prueba.model.Usuario;
import com.example.prueba.model.UsuarioDireccion;
import com.example.prueba.service.UsuarioDireccionService;
import com.example.prueba.service.UsuarioService;
import com.example.prueba.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDireccionService uDireccionService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthCredentialRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()));

            Usuario user = (Usuario) authenticate.getPrincipal();
            user.setPassword(null);
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtUtil.generateToken(user))
                    .body(user);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOpt1 = usuarioService.findByUsuario(usuario.getUsername());
        Optional<Usuario> usuarioOpt2 = usuarioService.findByCorreo(usuario.getCorreo());
        System.out.println(usuarioOpt2);

        if (usuarioOpt1.isPresent() || usuarioOpt2.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuario.setPassword(
                passwordEncoder.encode(usuario.getPassword()));

        Usuario usuarioAux = usuarioService.create(usuario);
        UsuarioDireccion uDireccion = new UsuarioDireccion("", "", 0, usuarioAux);
        uDireccionService.create(uDireccion);
        usuarioAux.setPassword(null);
        return new ResponseEntity<>(usuarioAux, HttpStatus.CREATED);
    }

}
