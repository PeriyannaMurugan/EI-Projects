package smartoffice.command;

import smartoffice.office.Office;
import smartoffice.office.Room;
import smartoffice.util.LoggerUtil;
import java.util.logging.Logger;

public class StatusRoomCommand implements Command {
    private Logger logger = LoggerUtil.getLogger();

    @Override
    public void execute(String[] args) {
        try {
            int roomId = Integer.parseInt(args[0]);
            Office office = Office.getInstance();

            if (!office.roomExists(roomId)) {
                System.out.println("Room " + roomId + " does not exist.");
                logger.warning("StatusRoom failed: Room " + roomId + " does not exist.");
                return;
            }

            office.releaseUnoccupiedRooms();

            Room room = office.getRoom(roomId);
            if (room.getCurrentOccupants() == 0) {
                System.out.println("Room " + roomId + " is currently unoccupied.");
            } else {
                System.out.println("Room " + roomId + " is occupied by " + room.getCurrentOccupants() + " persons.");
            }

            if (room.getBookingTime() != null) {
                System.out.println("Booking time: " + room.getBookingTime() + " for " + room.getBookingDuration() + " minutes.");
            } else {
                System.out.println("Room " + roomId + " is not booked.");
            }

            logger.info("Checked status for Room " + roomId);

        } catch (Exception e) {
            System.out.println("Invalid input for Status command.");
            logger.severe("StatusRoomCommand failed: " + e.getMessage());
        }
    }
}
