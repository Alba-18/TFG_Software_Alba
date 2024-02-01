package com.example.TfgSoftAlba.models.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.TfgSoftAlba.models.entity.User;

import java.util.List;


public interface UserService extends UserDetailsService{

    public User save(User user);

    public User get(long id);

    public List<User> listAllUsers();

    public boolean existsByEmail(String email);

    public User findByEmailAndName(String email, String name);
}
