package com.example.BMS.repository;

import com.example.BMS.model.*;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class BookingRepository {
    private final Map<Integer, Booking> bookings = new HashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public int save(Booking booking) {
        int id = idCounter.getAndIncrement();
        booking.setId(id);
        bookings.put(id, booking);
        return id;
    }

    public Booking getById(int id) {
        return bookings.get(id);
    }

    public void remove(int id) {
        bookings.remove(id);
    }

    public List<Booking> getByUser(String userName) {
        return bookings.values().stream()
                .filter(b -> b.getUserName().equals(userName))
                .collect(Collectors.toList());
    }
}