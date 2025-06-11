package com.example.BMS.model;

import java.time.LocalTime;

public class Slot {
    private String showName;
    private LocalTime startTime;
    private LocalTime endTime;
    private int capacity;

    private int bookingCount = 0;

    public int getBookingCount() {
        return bookingCount;
    }

    public void incrementBookingCount(int count) {
        this.bookingCount += count;
    }


    public Slot(String showName, LocalTime startTime, LocalTime endTime, int capacity) {
        this.showName = showName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
    }

    public String getShowName() {
        return showName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
