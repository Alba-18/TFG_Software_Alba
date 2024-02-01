package com.example.TfgSoftAlba.models.service.Impl;

import java.util.Collections;
import java.util.List;

import com.example.TfgSoftAlba.models.entity.Rol;
import com.example.TfgSoftAlba.models.entity.User;
import com.example.TfgSoftAlba.models.repository.RolRepository;
import com.example.TfgSoftAlba.models.repository.UserRepository;
import com.example.TfgSoftAlba.models.service.UserService;
import com.example.TfgSoftAlba.util.CustomUserDetails;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

	@Autowired
    private RolRepository rolRepository;


    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

	
    @Override
	public User get(long id) {
		User user =  userRepository.findById(id).get();
		return user;
	}


    @Override
	public User save(User user) {
		User userR = new User();
		if(user.getId() != null){//update user
			userR = user; 
		}else{
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			userR = new User(user.getEmail(), user.getName(), encodedPassword);
			Rol roles = rolRepository.findByName("user").get();
			userR.setRoles(Collections.singletonList(roles));
		} 
		return userRepository.save(userR);
	}

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		//return  new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), mapearAutoridadesRoles(user.getRoles()));
		return new CustomUserDetails(user);
	}

	@Override
	public boolean existsByEmail(String email){
		return userRepository.existsByEmail(email);
	}


	@Override
	public User findByEmailAndName(String email, String name) {
		return userRepository.findByEmailAndName(email, name);
	};
}
