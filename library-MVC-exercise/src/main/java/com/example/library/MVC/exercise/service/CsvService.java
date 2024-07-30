package com.example.library.MVC.exercise.service;

import com.example.library.MVC.exercise.model.Book;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class CsvService {
    public static final String BOOKS_FILE = "src/main/resources/books.csv";

//    creating a private field ArrayList<Book> = new ArrayList might fix the problem from libraryService.removeBook()
//    where the problem occurs because the addresses of #1 books and #2 books are not the same (they are different objets in memory, despite having the same contents)

    public Book searchBookByName(String title) {
//        #1 books (every time readAllBooks is called, it returns a new ArrayList in memory)
        ArrayList<Book> books = readAllBooks();
//        System.out.println("readAllBooks call in searchName: " + books);
        for(Book book : books) {
            if (Objects.equals(title, book.getTitle())) {
//                System.out.println("searchName: " + book);
                return book;
            }
        }
        return null;
    }

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

//    appends a book to the csv without overwriting the file's contents
    public void saveBook(Book book) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKS_FILE, true))) {
            String line = book.getTitle() + "," + book.getGenre() + "," + book.getAuthor() + "," + book.getYear() + "," + book.getPages();
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Something went wrong during the CSV writing process");
        }
    }

//    overwrites the file's contents with the list of books generated in this method
    public void saveAllBooks(ArrayList<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : books) {
                String line = book.getTitle() + "," + book.getGenre() + "," + book.getAuthor() + "," + book.getYear() + "," + book.getPages();
                writer.write(line);
                writer.write("9");
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong during the CSV writing process");
        }
    }
}
