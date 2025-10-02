package smartoffice.office;

import smartoffice.occupancy.OccupancySensor;
import smartoffice.util.LoggerUtil;
import java.time.LocalTime;
import java.util.*;
import java.util.logging.Logger;

public class Office {
    private static Office instance = null;
    private Map<Integer, Room> rooms = new HashMap<>();
    private Logger logger = LoggerUtil.getLogger();

    private Office() {}

    public static Office getInstance() {
        if (instance == null) instance = new Office();
        return instance;
    }

    public void configureRooms(int count) {
        rooms.clear();
        for (int i = 1; i <= count; i++) {
            Room room = new Room(i);
            room.addObserver(new OccupancySensor());
            rooms.put(i, room);
        }
        System.out.print("Office configured with " + count + " meeting rooms: ");
        rooms.values().forEach(r -> System.out.print("Room " + r.getRoomId() + ", "));
        System.out.println();
        logger.info("Configured " + count + " rooms.");
    }

    public Room getRoom(int roomId) {
        return rooms.get(roomId);
    }

    public boolean roomExists(int roomId) {
        return rooms.containsKey(roomId);
    }

    public void releaseUnoccupiedRooms() {
        LocalTime now = LocalTime.now();
        for (Room room : rooms.values()) {
            if (room.getCurrentOccupants() == 0 && room.getBookingTime() != null) {
                LocalTime bookedTime = room.getBookingTime();
                if (now.isAfter(bookedTime.plusMinutes(5))) {
                    room.cancelBooking();
                    System.out.println("Room " + room.getRoomId() + " is now unoccupied. Booking released. AC and lights off.");
                    logger.info("Room " + room.getRoomId() + " auto-released due to inactivity.");
                }
            }
        }
    }
}
