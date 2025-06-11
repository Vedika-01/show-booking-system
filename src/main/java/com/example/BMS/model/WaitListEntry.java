package com.example.BMS.model;

import java.time.LocalTime;


public class WaitListEntry {
    private String userName;
    private String showName;
    private LocalTime startTime;
    private int numberOfPersons;

    public WaitListEntry(String userName, String showName, LocalTime startTime, int numberOfPersons) {
        this.userName = userName;
        this.showName = showName;
        this.startTime = startTime;
        this.numberOfPersons = numberOfPersons;
    }

    public String getUserName() {
        return userName;
    }

    public String getShowName() {
        return showName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }
}
