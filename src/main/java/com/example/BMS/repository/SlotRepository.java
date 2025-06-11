package com.example.BMS.repository;

import com.example.BMS.model.Slot;
import com.example.BMS.model.SlotKey;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class SlotRepository {

    private final Map<SlotKey, Slot> slotMap = new HashMap<>();

    public void addSlot(SlotKey slotKey, Slot slot) {
        slotMap.put(slotKey, slot);
    }

    public Slot getSlot(SlotKey slotKey) {
        return slotMap.get(slotKey);
    }

    public Map<SlotKey, Slot> getSlotsByShow(String showName) {
        return slotMap.entrySet().stream()
                .filter(entry -> entry.getKey().getShowName().equals(showName))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
