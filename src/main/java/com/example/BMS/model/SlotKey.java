package com.example.BMS.model;

import java.time.LocalTime;
import java.util.Objects;

public class SlotKey {
    private final String showName;
    private final LocalTime startTime;

    public SlotKey(String showName, LocalTime startTime) {
        this.showName = showName;
        this.startTime = startTime;
    }

    public String getShowName() {
        return showName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SlotKey)) return false;
        SlotKey slotKey = (SlotKey) o;
        return Objects.equals(showName, slotKey.showName) &&
                Objects.equals(startTime, slotKey.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(showName, startTime);
    }
}
