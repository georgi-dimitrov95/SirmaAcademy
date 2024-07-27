package com.example.library.MVC.exercise.service;

import com.example.library.MVC.exercise.model.Book;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class CsvService {
    private static final String BOOKS_FILE = "src/main/resources/books.csv";

    public ArrayList<Book> readAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.trim().split(",");
                Book book = new Book(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4]));
                books.add(book);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong during the CSV reading process.");
        }
        return books;
    }
}
