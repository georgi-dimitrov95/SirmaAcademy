package com.academy.flightsystem.api.service;

import com.academy.flightsystem.api.model.UserInfo;
import com.academy.flightsystem.api.model.dto.RegisterUserDto;
import com.academy.flightsystem.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    public UserInfo register(RegisterUserDto userDto) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userDto.getUsername());
        userInfo.setPassword(userDto.getPassword());
        userInfo.setRoles("USER");
        userInfo.setTickets(new ArrayList<>());

        return userRepository.save(userInfo);
    }
}
