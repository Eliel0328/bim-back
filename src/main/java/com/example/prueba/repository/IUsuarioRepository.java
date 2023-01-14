package com.example.prueba.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prueba.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsuario(String username);

    Optional<Usuario> findByCorreo(String correo);
}
