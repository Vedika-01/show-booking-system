package com.example.BMS.service;

import com.example.BMS.controller.AuthController;
import com.example.BMS.model.Booking;
import com.example.BMS.model.Slot;
import com.example.BMS.model.WaitListEntry;
import com.example.BMS.repository.BookingRepository;
import com.example.BMS.repository.ShowRepository;
import com.example.BMS.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {
    private final ShowRepository showRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    private final AuthController authController;

    public ShowService(ShowRepository showRepository, BookingRepository bookingRepository, UserRepository userRepository, AuthController authController) {
        this.showRepository = showRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.authController = authController;
    }

    public void onboardShow(String showName, String genre, String organizerName) {
        System.out.println("Onboarding show: " + showName + " by organizer: " + organizerName);

        if (!authController.isOrganizerLoggedIn(organizerName)) {
            throw new IllegalArgumentException("Organizer not found: " + organizerName);
        }

        showRepository.registerShow(showName, genre, organizerName);
    }

    public int bookTicket(String userName, String showName, LocalTime startTime, int numberOfPersons) {
        if (!authController.isUserLoggedIn(userName)) {
            throw new IllegalArgumentException("User not found: " + userName);
        }

        Slot slot = showRepository.getSlot(showName, startTime);
        if (slot != null && slot.getCapacity() >= numberOfPersons) {
            slot.setCapacity(slot.getCapacity() - numberOfPersons);

            // ✅ FIX: update booking count here
            slot.incrementBookingCount(numberOfPersons);

            Booking booking = new Booking(0, userName, showName, startTime, numberOfPersons);
            return bookingRepository.save(booking);
        } else {
            showRepository.addToWaitList(userName, showName, startTime, numberOfPersons);
            return -1;
        }
    }



    public void onboardShowSlot(String showName, LocalTime startTime, LocalTime endTime, int capacity) {

        showRepository.saveSlot(showName, startTime, endTime, capacity);
    }

    public List<Slot> getAvailableShowsByGenre(String genre) {
        return showRepository.getAvailableSlotsByGenre(genre);
    }



    public boolean cancelBooking(int bookingId) {
        Booking booking = bookingRepository.getById(bookingId);
        if (booking == null) return false;

        Slot slot = showRepository.getSlot(booking.getShowName(), booking.getStartTime());
        if (slot != null) {
            slot.setCapacity(slot.getCapacity() + booking.getNumberOfPersons());
        }

        bookingRepository.remove(bookingId);

        WaitListEntry nextEntry = showRepository.popFromWaitList(booking.getShowName(), booking.getStartTime());
        if (nextEntry != null && slot.getCapacity() >= nextEntry.getNumberOfPersons()) {
            slot.setCapacity(slot.getCapacity() - nextEntry.getNumberOfPersons());
            slot.incrementBookingCount(nextEntry.getNumberOfPersons());
            Booking waitListedBooking = new Booking(0, nextEntry.getUserName(), nextEntry.getShowName(), nextEntry.getStartTime(), nextEntry.getNumberOfPersons());
            bookingRepository.save(waitListedBooking);
        }
        return true;
    }

    public List<Booking> getBookingsForUser(String userName) {
        return bookingRepository.getByUser(userName);
    }

    public List<Slot> getShowsByOrganizer(String organizerName) {
        return showRepository.getSlotsByOrganizer(organizerName);
    }

    public List<Slot> getTrendingShows() {
        return showRepository.getAllSlots().stream()
                .sorted(Comparator.comparingInt(Slot::getBookingCount).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

}
