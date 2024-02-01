package com.example.TfgSoftAlba.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TfgSoftAlba.models.entity.Rol;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByName(String name);
}
