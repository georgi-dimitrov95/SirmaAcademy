package com.example.library.MVC.exercise.service;

import com.example.library.MVC.exercise.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class LibraryService {
    @Autowired
    public CsvService csvService;

    public ArrayList<Book> getAllBooks() {
        return csvService.readAllBooks();
    }

    public void addBook(Book book) {
        csvService.saveBook(book);
    }

    public void removeBook(String title) {
        Book book = csvService.searchBookByName(title);
        if (book != null) {
            ArrayList<Book> books = csvService.readAllBooks();
            books.remove(book);
            csvService.saveAllBooks(books);
        }
    }
}
