package com.example.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prueba.model.Authority;

public interface IAuthorityRepository extends JpaRepository<Authority, Integer> {

}
