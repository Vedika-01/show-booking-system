package com.example.BMS.repository;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class UserRepository {
    private final Set<String> users = new HashSet<>();
    private final Set<String> organizers = new HashSet<>();





    public boolean addUser(String username) {
        return users.add(username);
    }

    public boolean addOrganizer(String organizer) {
        return organizers.add(organizer);
    }

    public boolean isUserExists(String username) {
        return users.contains(username);
    }

    public boolean isOrganizerExists(String organizer) {
        return organizers.contains(organizer);
    }
}
