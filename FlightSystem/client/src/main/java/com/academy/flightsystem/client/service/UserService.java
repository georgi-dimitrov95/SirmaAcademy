package com.academy.flightsystem.client.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Value("$backend.api.url")
    private String backendApiUrl;
}
