package smartoffice.office;

import smartoffice.occupancy.RoomObserver;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomId;
    private int maxCapacity = 10;
    private int currentOccupants = 0;
    private LocalTime bookingTime = null;
    private int bookingDuration = 0;
    private List<RoomObserver> observers = new ArrayList<>();

    public Room(int roomId) { this.roomId = roomId; }

    public int getRoomId() { return roomId; }
    public int getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(int capacity) { this.maxCapacity = capacity; }

    public int getCurrentOccupants() { return currentOccupants; }
    public void setCurrentOccupants(int occupants) {
        this.currentOccupants = occupants;
        notifyObservers();
    }

    public LocalTime getBookingTime() { return bookingTime; }
    public int getBookingDuration() { return bookingDuration; }

    public void setBooking(LocalTime time, int duration) {
        this.bookingTime = time;
        this.bookingDuration = duration;
    }

    public void cancelBooking() {
        this.bookingTime = null;
        this.bookingDuration = 0;
    }

    public void addObserver(RoomObserver observer) { observers.add(observer); }

    private void notifyObservers() {
        for (RoomObserver o : observers) o.update(this);
    }
    public boolean isBookedDuring(LocalTime start, int duration) {
    if (bookingTime == null) return false;
    LocalTime endExisting = bookingTime.plusMinutes(bookingDuration);
    LocalTime endNew = start.plusMinutes(duration);
    return !(endNew.isBefore(bookingTime) || start.isAfter(endExisting));
}

}
