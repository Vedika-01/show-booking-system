package com.example.BMS.model;

public class Show {
    private String showName;
    private String genre;
    private String organizerName;

    public Show(String showName, String genre, String organizerName) {
        this.showName = showName;
        this.genre = genre;
        this.organizerName = organizerName;
    }

    public String getShowName() {
        return showName;
    }

    public String getGenre() {
        return genre;
    }

    public String getOrganizerName() {
        return organizerName;
    }


}
