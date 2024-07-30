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
            System.out.println("removeBook: " + book);
//            #2 books
            ArrayList<Book> books = csvService.readAllBooks();
//            System.out.println("Books from removeBook:" + books);
//            the problem is in the line below
            books.remove(book);
            csvService.saveAllBooks(books);
        }
    }
}
