package com.example.restdbapp.models;

public class Show {
    private int id;
    private String title;
    private int year;
    private int episodes;

    public Show(int id, String title, int year, int episodes) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.episodes = episodes;
    }

    public Show() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }
}
