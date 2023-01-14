package com.example.prueba.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba.model.Usuario;
import com.example.prueba.repository.IUsuarioRepository;

@Service
public class UsuarioService implements ICRUDService<Usuario> {
    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Override
    public Usuario create(Usuario usuario) {
        return iUsuarioRepository.save(usuario);
    }

    @Override
    public void delete(Integer id) {
        iUsuarioRepository.deleteById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return iUsuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return iUsuarioRepository.findById(id);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return iUsuarioRepository.save(usuario);
    }
}
