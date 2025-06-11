package com.example.BMS.controller;

import com.example.BMS.model.Booking;
import com.example.BMS.model.Slot;
import com.example.BMS.service.ShowService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowService showService;
    private final AuthController authController;

    public ShowController(ShowService showService, AuthController authController) {
        this.showService = showService;
        this.authController = authController;
    }

    @PostMapping("/onboard")
    public String onboardShow(@RequestParam String organizer,
                              @RequestParam String showName,
                              @RequestParam String genre) {

        System.out.println("Request to onboard show: " + showName + " by organizer: " + organizer);

        if (!authController.isOrganizerLoggedIn(organizer)) {
            return "Organizer not logged in.";
        }

        try {
            showService.onboardShow(showName, genre, organizer);
            return "Show onboarded successfully.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }


    @PostMapping("/add-slot")
    public String addSlot(@RequestParam String organizer,
                          @RequestParam String showName,
                          @RequestParam String startTime,
                          @RequestParam String endTime,
                          @RequestParam int capacity) {

        if (!authController.isOrganizerLoggedIn(organizer)) {
            return "Organizer not logged in.";
        }

        showService.onboardShowSlot(showName, LocalTime.parse(startTime), LocalTime.parse(endTime), capacity);
        System.out.println("Request to add slot for show: " + showName + " by organizer: " + organizer + "  Capacity now is :" + capacity);

        return "Slot added successfully.";
    }

    @PostMapping("/book")
    public String bookShow(@RequestParam String user,
                           @RequestParam String showName,
                           @RequestParam String startTime,
                           @RequestParam int numberOfPersons) {

        System.out.println("Booking request from user: " + user + " for show: " + showName);

        if (!authController.isUserLoggedIn(user)) {
            return "User not logged in.";
        }

        try {
            int bookingId = showService.bookTicket(user, showName, LocalTime.parse(startTime), numberOfPersons);
            return bookingId == -1
                    ? "Show is full. Added to waitlist."
                    : "Booking successful. Booking ID: " + bookingId;
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }


    @PostMapping("/cancel")
    public String cancelBooking(@RequestParam int bookingId) {
        System.out.println("Cancel request for booking ID: " + bookingId);

        boolean success = showService.cancelBooking(bookingId);
        return success ? "Booking cancelled successfully." : "Booking not found.";
    }

    @GetMapping("/bookings")
    public List<Booking> getBookingsForUser(@RequestParam String user) {
        System.out.println("Fetching bookings for user: " + user);

        return showService.getBookingsForUser(user);
    }

    @GetMapping("/organizer-shows")
    public List<Slot> getShowsByOrganizer(@RequestParam String organizer) {
        System.out.println("Fetching shows created by organizer: " + organizer);

        return showService.getShowsByOrganizer(organizer);
    }

    @GetMapping("/trending")
    public List<Slot> getTrendingShows() {
        System.out.println("Fetching trending shows");

        return showService.getTrendingShows();
    }

    @GetMapping("/by-genre")
    public List<Slot> getByGenre(@RequestParam String genre) {
        System.out.println("Fetching shows by genre: " + genre);

        return showService.getAvailableShowsByGenre(genre);
    }
}
