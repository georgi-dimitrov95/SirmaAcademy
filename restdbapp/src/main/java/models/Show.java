package models;

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
}
