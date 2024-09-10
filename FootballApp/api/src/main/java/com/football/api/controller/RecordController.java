package com.football.api.controller;

import com.football.api.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PostMapping("/csv")
    public String read() {
        try  {
            recordService.csvToDatabase();
            return "Success";
        } catch (RuntimeException e) {
            return "Failed";
        }
    }
}
