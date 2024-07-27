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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
