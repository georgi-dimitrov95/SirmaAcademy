package com.example.library.MVC.exercise.controller;


import com.example.library.MVC.exercise.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @GetMapping("/books")
    public String viewAllBooks(Model model) {
        model.addAttribute("books", libraryService.getAllBooks());
        return "books";
    }
}
