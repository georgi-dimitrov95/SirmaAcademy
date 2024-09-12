package com.football.api.controller;

import com.football.api.model.Pair;
import com.football.api.model.dto.PairDto;
import com.football.api.service.SharedMinutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/minutes")
public class MinutesSharedController {

    @Autowired
    private SharedMinutesService sharedMinutesService;

    @GetMapping("/shared/all")
    public List<Pair> playersWithSharedMinutes() {
        return sharedMinutesService.getPairsWithSharedMinutes();
    }

    @GetMapping("/shared/most")
    public List<PairDto> mostMinutesShared() {
        return sharedMinutesService.pairDTOsWithMostMinutesShared();
    }
}
