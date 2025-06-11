package com.example.BMS.repository;

import com.example.BMS.model.Slot;
import com.example.BMS.model.WaitListEntry;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.*;

@Repository
public class ShowRepository {

    private final Map<String, List<Slot>> showSlots = new HashMap<>();
    private final Map<String, String> showToGenre = new HashMap<>();
    private final Map<String, String> showToOrganizer = new HashMap<>();
    private final Map<String, List<WaitListEntry>> waitLists = new HashMap<>();
    private final Set<String> organizers = new HashSet<>();

    public void registerOrganizer(String organizerName) {
        organizers.add(organizerName);
    }

    public boolean isOrganizerExists(String organizerName) {
        return organizers.contains(organizerName);
    }

    public void registerShow(String showName, String genre, String organizerName) {
        showSlots.putIfAbsent(showName, new ArrayList<>());
        showToGenre.put(showName, genre);
        showToOrganizer.put(showName, organizerName);
    }

    public void saveSlot(String showName, LocalTime startTime, LocalTime endTime, int capacity) {
        Slot slot = new Slot(showName, startTime, endTime, capacity);
        showSlots.get(showName).add(slot);
    }

    public Slot getSlot(String showName, LocalTime startTime) {
        List<Slot> slots = showSlots.get(showName);
        if (slots == null) return null;
        for (Slot slot : slots) {
            if (slot.getStartTime().equals(startTime)) {
                return slot;
            }
        }
        return null;
    }

    public void addToWaitList(String userName, String showName, LocalTime startTime, int numberOfPersons) {
        String key = showName + "|" + startTime.toString();
        WaitListEntry entry = new WaitListEntry(userName, showName, startTime, numberOfPersons);
        waitLists.computeIfAbsent(key, k -> new LinkedList<>()).add(entry);
    }

    public WaitListEntry popFromWaitList(String showName, LocalTime startTime) {
        String key = showName + "|" + startTime.toString();
        List<WaitListEntry> list = waitLists.get(key);
        if (list != null && !list.isEmpty()) {
            return list.remove(0);
        }
        return null;
    }

    public List<Slot> getAvailableSlotsByGenre(String genre) {
        List<Slot> result = new ArrayList<>();
        for (String showName : showToGenre.keySet()) {
            if (showToGenre.get(showName).equalsIgnoreCase(genre)) {
                result.addAll(showSlots.getOrDefault(showName, Collections.emptyList()));
            }
        }
        return result;
    }

    public List<Slot> getSlotsByOrganizer(String organizerName) {
        List<Slot> result = new ArrayList<>();
        for (String showName : showToOrganizer.keySet()) {
            if (showToOrganizer.get(showName).equals(organizerName)) {
                result.addAll(showSlots.getOrDefault(showName, Collections.emptyList()));
            }
        }
        return result;
    }

    public List<Slot> getAllSlots() {
        List<Slot> result = new ArrayList<>();
        for (List<Slot> slots : showSlots.values()) {
            result.addAll(slots);
        }
        return result;
    }
}
