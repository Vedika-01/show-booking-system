package com.example.BMS.controller;

import com.example.BMS.repository.ShowRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final ShowRepository showRepository;

    private Set<String> users = new HashSet<>();
    private Set<String> organizers = new HashSet<>();
    private Set<String> loggedInUsers = new HashSet<>();
    private Set<String> loggedInOrganizers = new HashSet<>();

    public AuthController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @PostMapping("/signup/user")
    public String signupUser(@RequestParam String userName) {
        if (users.contains(userName)) {
            return "User already exists.";
        }
        users.add(userName);
        System.out.println("User signed up: " + userName);
        return "User registered successfully.";
    }

    @PostMapping("/login/user")
    public String loginUser(@RequestParam String userName) {
        if (!users.contains(userName)) {
            return "User not found.";
        }
        loggedInUsers.add(userName);
        System.out.println("User logged in: " + userName);
        return "User logged in successfully.";
    }

    @PostMapping("/signup/organizer")
    public String signupOrganizer(@RequestParam String organizerName) {
        if (organizers.contains(organizerName)) {
            return "Organizer already exists.";
        }
        organizers.add(organizerName);
        showRepository.registerOrganizer(organizerName);
        System.out.println("Organizer signed up: " + organizerName);
        return "Organizer registered successfully.";
    }

    @PostMapping("/login/organizer")
    public String loginOrganizer(@RequestParam String organizerName) {
        if (!organizers.contains(organizerName)) {
            return "Organizer not found.";
        }
        loggedInOrganizers.add(organizerName);
        System.out.println("Organizer logged in: " + organizerName);
        System.out.println("Organizers: " + organizers);
        return "Organizer logged in successfully.";
    }

    // Utility methods for internal use
    public boolean isUserLoggedIn(String userName) {
        return loggedInUsers.contains(userName);
    }

    public boolean isOrganizerLoggedIn(String organizerName) {
        return loggedInOrganizers.contains(organizerName);
    }
}