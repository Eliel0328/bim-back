package com.example.prueba.controller;

import java.util.List;

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

import com.example.prueba.model.UsuarioDireccion;
import com.example.prueba.service.UsuarioDireccionService;

@RestController
@RequestMapping("/api/direccionUsuario")
public class UsuarioDireccionController {
    @Autowired
    private UsuarioDireccionService udservice;

    @GetMapping
    public ResponseEntity<List<UsuarioDireccion>> findAll() {
        return ResponseEntity.ok(udservice.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDireccion> findById(@PathVariable("id") Integer id) {
        return udservice.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UsuarioDireccion> create(@Valid @RequestBody UsuarioDireccion uDireccion) {
        return new ResponseEntity<>(udservice.create(uDireccion), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UsuarioDireccion> update(@Valid @RequestBody UsuarioDireccion uDireccion) {
        System.out.println(uDireccion);
        return udservice.findById(uDireccion.getIdUsuarioDireccion())
                .map(c -> ResponseEntity.ok(udservice.update(uDireccion)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
}
