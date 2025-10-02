package smartoffice.command;

import smartoffice.office.Office;
import smartoffice.office.Room;
import smartoffice.util.LoggerUtil;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

public class BlockRoomCommand implements Command {
    private Logger logger = LoggerUtil.getLogger();

    @Override
    public void execute(String[] args) {
        try {
            int roomId = Integer.parseInt(args[0]);
            LocalTime time = LocalTime.parse(args[1]);
            int duration = Integer.parseInt(args[2]);

            Office office = Office.getInstance();
            if (!office.roomExists(roomId)) {
                System.out.println("Invalid room number. Please enter a valid room number.");
                logger.warning("BlockRoom failed: invalid room " + roomId);
                return;
            }

            Room room = office.getRoom(roomId);
           if (room.isBookedDuring(time, duration)) {
    System.out.println("Room " + roomId + " is already booked during this time. Cannot book.");
    logger.warning("Attempted booking failed: Room " + roomId + " already booked.");
    return;
}


            room.setBooking(time, duration);
            System.out.println("Room " + roomId + " booked from " + time + " for " + duration + " minutes.");
            logger.info("Room " + roomId + " booked from " + time + " for " + duration + " minutes.");

        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Use HH:MM.");
            logger.warning("Invalid time format in BlockRoomCommand");
        } catch (Exception e) {
            System.out.println("Invalid input for BlockRoom command.");
            logger.severe("BlockRoomCommand failed: " + e.getMessage());
        }
    }
}
