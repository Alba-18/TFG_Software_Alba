package com.example.TfgSoftAlba.models.repository;

import com.example.TfgSoftAlba.models.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String email);
   
    boolean existsByEmail(String email);

    User findByEmailAndName(String email, String name);
}
