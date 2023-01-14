package com.example.prueba.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prueba.model.Usuario;
import com.example.prueba.model.UsuarioDireccion;

public interface IUsuarioDireccionRepository extends JpaRepository<UsuarioDireccion, Integer> {
    public Optional<UsuarioDireccion> findByUsuario(Usuario usuario);
}
