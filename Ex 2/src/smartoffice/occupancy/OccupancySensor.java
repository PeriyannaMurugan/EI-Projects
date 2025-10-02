package smartoffice.occupancy;

import smartoffice.office.Room;
import smartoffice.util.LoggerUtil;
import java.util.logging.Logger;

public class OccupancySensor implements RoomObserver {
    private Logger logger = LoggerUtil.getLogger();

    @Override
    public void update(Room room) {
        if (room.getCurrentOccupants() >= 2) {
            String msg = "Room " + room.getRoomId() + " is now occupied by "
                + room.getCurrentOccupants() + " persons. AC and lights turned on.";
            System.out.println(msg);
            logger.info(msg);
        } else if (room.getCurrentOccupants() == 0) {
            String msg = "Room " + room.getRoomId() + " is now unoccupied. AC and lights turned off.";
            System.out.println(msg);
            logger.info(msg);
        } else {
            String msg = "Room " + room.getRoomId() + " occupancy insufficient to mark as occupied.";
            System.out.println(msg);
            logger.info(msg);
        }
    }
}
