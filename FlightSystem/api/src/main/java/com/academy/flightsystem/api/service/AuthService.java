package com.academy.flightsystem.api.service;

import com.academy.flightsystem.api.model.Role;
import com.academy.flightsystem.api.model.UserInfo;
import com.academy.flightsystem.api.model.dto.RegisterUserDto;
import com.academy.flightsystem.api.repository.RoleRepository;
import com.academy.flightsystem.api.repository.UserRepository;
import com.academy.flightsystem.api.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

//   Registers a new user in the system by encoding their password and saving their info in the database via the userRepository
    public UserInfo register(RegisterUserDto userDto) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userDto.getUsername());
        userInfo.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Set<Role> roles = Set.of(roleRepository.findByName("ROLE_USER").orElseThrow());
        userInfo.setRoles(roles);
        return userRepository.save(userInfo);
    }
}
