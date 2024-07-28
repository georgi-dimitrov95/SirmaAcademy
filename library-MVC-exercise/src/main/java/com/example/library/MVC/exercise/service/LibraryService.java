package com.example.library.MVC.exercise.service;

import com.example.library.MVC.exercise.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class LibraryService {
    @Autowired
    private CsvService csvService;

    public ArrayList<Book> getAllBooks() {
        return csvService.readAllBooks();
    }

    public void addBook(Book book) {
        csvService.saveBook(book);
    }

    
}
