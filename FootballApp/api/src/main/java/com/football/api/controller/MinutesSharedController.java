package com.football.api.controller;

import com.football.api.model.Pair;
import com.football.api.service.RecordService;
import com.football.api.service.SharedMinutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/minutes")
public class MinutesSharedController {

    @Autowired
    private SharedMinutesService sharedMinutesService;

    @GetMapping("/shared")
    public Set<Pair> minutesShared() {
        return sharedMinutesService.getPairWithMostSharedMinutes();
    }
}
