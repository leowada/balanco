package com.wada.balanco.login.service;

import com.wada.balanco.login.model.Role;
import com.wada.balanco.login.model.UserCustom;
import com.wada.balanco.login.repository.RoleRepository;
import com.wada.balanco.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserCustom findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserCustom saveUser(UserCustom user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setAtivo(true);
        Role userRole = roleRepository.findByDescricao("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
        return user;
    }

}
