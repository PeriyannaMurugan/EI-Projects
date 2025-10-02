package smartoffice.command;

import smartoffice.office.Office;
import smartoffice.office.Room;
import smartoffice.util.LoggerUtil;
import java.util.logging.Logger;

public class CancelRoomCommand implements Command {
    private Logger logger = LoggerUtil.getLogger();

    @Override
    public void execute(String[] args) {
        try {
            int roomId = Integer.parseInt(args[0]);
            Office office = Office.getInstance();

            if (!office.roomExists(roomId)) {
                System.out.println("Room " + roomId + " does not exist.");
                logger.warning("CancelRoom failed: Room " + roomId + " does not exist.");
                return;
            }

            Room room = office.getRoom(roomId);
            if (room.getBookingTime() == null) {
                System.out.println("Room " + roomId + " is not booked. Cannot cancel booking.");
                logger.warning("CancelRoom failed: Room " + roomId + " not booked.");
                return;
            }

            room.cancelBooking();
            System.out.println("Booking for Room " + roomId + " cancelled successfully.");
            logger.info("Booking cancelled for Room " + roomId);

        } catch (Exception e) {
            System.out.println("Invalid input for CancelRoom command.");
            logger.severe("CancelRoomCommand failed: " + e.getMessage());
        }
    }
}
