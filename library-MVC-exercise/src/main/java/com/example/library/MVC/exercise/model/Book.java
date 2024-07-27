package com.example.library.MVC.exercise.model;

public class Book {
    private String name;
    private String genre;
    private String author;
    int year;
    int pages;

    public Book(String name, String genre, String author, int year, int pages) {
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    public Book() {

    }
}
