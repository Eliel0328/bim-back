package com.example.prueba.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba.model.Usuario;
import com.example.prueba.model.UsuarioDireccion;
import com.example.prueba.service.UsuarioDireccionService;
import com.example.prueba.service.UsuarioService;

@RestController
@RequestMapping("/api/direccionUsuario")
public class UsuarioDireccionController {
    @Autowired
    private UsuarioDireccionService udservice;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDireccion>> findAll() {
        return ResponseEntity.ok(udservice.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDireccion> findById(@PathVariable("id") Integer id) {
        Optional<Usuario> usuarioOpt = usuarioService.findById(id);

        if (!usuarioOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return udservice.findByUsuario(usuarioOpt.get())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<UsuarioDireccion> update(@RequestBody UsuarioDireccion uDireccion) {
        return udservice.findById(uDireccion.getIdUsuarioDireccion())
                .map(c -> ResponseEntity.ok(udservice.update(uDireccion)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
}
