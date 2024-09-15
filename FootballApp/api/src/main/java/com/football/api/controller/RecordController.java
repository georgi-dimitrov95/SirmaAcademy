package com.football.api.controller;

import com.football.api.model.Record;
import com.football.api.model.dto.RecordDto;
import com.football.api.service.RecordService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PostMapping("/csv")
    public String read() {
        try  {
            recordService.listToDatabase();
            return "Success";
        } catch (IOException e) {
            return "Could not read CSV file";
        }
    }

    @GetMapping("/get")
    public List<Record> getAllRecords() {
        return recordService.getAllRecords();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getRecord(@PathVariable Long id) {
        try {
            RecordDto recordDto = recordService.getRecord(id);
            return ResponseEntity.ok(recordDto);
        } catch (EntityNotFoundException e) {
            String errorMessage = "Record not found with ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id) {
        try {
            recordService.deleteRecord(id);
            String successMessage = "Successfully deleted record with ID: " + id;
            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
        } catch (EntityNotFoundException e) {
            String errorMessage = "Record not found with ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
}
