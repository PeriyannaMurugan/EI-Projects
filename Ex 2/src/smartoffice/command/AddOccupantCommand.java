package smartoffice.command;

import smartoffice.office.Office;
import smartoffice.office.Room;
import smartoffice.util.LoggerUtil;
import java.util.logging.Logger;

public class AddOccupantCommand implements Command {
    private Logger logger = LoggerUtil.getLogger();

    @Override
    public void execute(String[] args) {
        try {
            int roomId = Integer.parseInt(args[0]);
            int occupants = Integer.parseInt(args[1]);
            Office office = Office.getInstance();

            if (!office.roomExists(roomId)) {
                System.out.println("Room " + roomId + " does not exist.");
                logger.warning("AddOccupant failed: Room " + roomId + " does not exist.");
                return;
            }

            Room room = office.getRoom(roomId);
            if (occupants < 0 || occupants > room.getMaxCapacity()) {
                System.out.println("Invalid occupancy. Must be between 0 and " + room.getMaxCapacity());
                logger.warning("Invalid occupancy " + occupants + " for Room " + roomId);
                return;
            }

            room.setCurrentOccupants(occupants);
            logger.info("Added " + occupants + " occupants to Room " + roomId);

        } catch (Exception e) {
            System.out.println("Invalid input for AddOccupant command.");
            logger.severe("AddOccupantCommand failed: " + e.getMessage());
        }
    }
}
