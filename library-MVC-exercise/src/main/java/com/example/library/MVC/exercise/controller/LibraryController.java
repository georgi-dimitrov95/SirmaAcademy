package com.example.library.MVC.exercise.controller;


import com.example.library.MVC.exercise.model.Book;
import com.example.library.MVC.exercise.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LibraryController {
    @Autowired
    public LibraryService libraryService;

    @GetMapping("/books")
    public String viewAllBooks(Model model) {
        model.addAttribute("books", libraryService.getAllBooks());
        model.addAttribute("book", new Book());
        return "books";
    }

    @PostMapping("/books")
    public String addBook(@ModelAttribute Book book) {
        libraryService.addBook(book);
        return "redirect:/books";
    }
}
