package com.example.BMS.service;

import com.example.BMS.model.Booking;
import com.example.BMS.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public int createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking getBookingById(int id) {
        return bookingRepository.getById(id);
    }

    public void deleteBooking(int id) {
        bookingRepository.remove(id);
    }

    public List<Booking> getBookingsByUser(String userName) {
        return bookingRepository.getByUser(userName);
    }
}