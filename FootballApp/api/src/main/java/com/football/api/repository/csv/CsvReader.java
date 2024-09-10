package com.football.api.repository.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class CsvReader {

    private static final Logger logger = LoggerFactory.getLogger(CsvReader.class);

    public ArrayList<String[]> readFromCSV(String filePath) throws IOException {
        ArrayList<String[]> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] fields = line.split(",");

//                    trims each element
                    for (int i = 0; i < fields.length; i++) {
                        fields[i] = fields[i].trim();
                    }

                    rows.add(fields);
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("No file found at path: {}", filePath, e);
        }
        return rows;
    }
}
