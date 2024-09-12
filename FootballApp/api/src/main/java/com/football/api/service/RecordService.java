package com.football.api.service;

import com.football.api.model.Record;
import com.football.api.model.dto.RecordDto;
import com.football.api.repository.csv.RecordCsvReader;
import com.football.api.repository.jpa.RecordJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {
    public static final String TEAMS_FILE = "src/main/resources/csv/records.csv";

    @Autowired
    RecordJpaRepository recordJpaRepository;

    @Autowired
    RecordCsvReader recordCsvReader;

    public void listToDatabase() throws IOException {
        try {
            ArrayList<Record> records = recordCsvReader.csvToList(TEAMS_FILE);
            recordJpaRepository.saveAll(records);
        } catch (IOException e) {
            throw new IOException("Invalid data and/or file path");
        }
    }

    public List<Record> getAllRecords() {
        return recordJpaRepository.findAll();
    }

    public RecordDto getRecord(Long id) {
        Record record = recordJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new RecordDto(record);
    }
}
