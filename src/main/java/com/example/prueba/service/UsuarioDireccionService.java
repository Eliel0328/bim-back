package com.example.prueba.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba.model.Usuario;
import com.example.prueba.model.UsuarioDireccion;
import com.example.prueba.repository.IUsuarioDireccionRepository;

@Service
public class UsuarioDireccionService implements ICRUDService<UsuarioDireccion> {
    @Autowired
    private IUsuarioDireccionRepository iUsuarioDireccionRepository;

    @Override
    public UsuarioDireccion create(UsuarioDireccion usuario) {
        return iUsuarioDireccionRepository.save(usuario);
    }

    @Override
    public void delete(Integer id) {
        iUsuarioDireccionRepository.deleteById(id);
    }

    @Override
    public List<UsuarioDireccion> findAll() {
        return iUsuarioDireccionRepository.findAll();
    }

    @Override
    public Optional<UsuarioDireccion> findById(Integer id) {
        return iUsuarioDireccionRepository.findById(id);
    }

    @Override
    public UsuarioDireccion update(UsuarioDireccion usuario) {
        return iUsuarioDireccionRepository.save(usuario);
    }

    public Optional<UsuarioDireccion> findByUsuario(Usuario usuario) {
        return iUsuarioDireccionRepository.findByUsuario(usuario);
    }

}
