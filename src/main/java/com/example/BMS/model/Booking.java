package com.example.BMS.model;

import java.time.LocalTime;

public class Booking {
    private int id;
    private String userName;
    private String showName;
    private LocalTime startTime;
    private int numberOfPersons;

    public Booking(int id, String userName, String showName, LocalTime startTime, int numberOfPersons) {
        this.id = id;
        this.userName = userName;
        this.showName = showName;
        this.startTime = startTime;
        this.numberOfPersons = numberOfPersons;
    }


    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for userName
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter and Setter for showName
    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    // Getter and Setter for startTime
    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    // Getter and Setter for numberOfPersons
    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }
}
