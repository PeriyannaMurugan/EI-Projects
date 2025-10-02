package smartoffice.command;

import smartoffice.office.Office;
import smartoffice.util.LoggerUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CommandRegistry {
    private Map<String, Command> commands = new HashMap<>();
    private Logger logger = LoggerUtil.getLogger();

    public CommandRegistry() {
        commands.put("add", new AddOccupantCommand());
        commands.put("block", new BlockRoomCommand());
        commands.put("cancel", new CancelRoomCommand());
        commands.put("status", new StatusRoomCommand());
    }

    public void executeCommand(String input) {
        if (input == null || input.isEmpty()) return;

        String[] parts = input.trim().split("\\s+");
        if (parts.length < 2) {
        System.out.println("Usage: config room max <roomId> <capacity>");  
         return;
        }
        

        String cmd = parts[0].toLowerCase();     // Config / Add / Block / Cancel / Status
        String action = parts[1].toLowerCase();  // room / occupant / etc.

        String[] args = new String[parts.length - 2];
        System.arraycopy(parts, 2, args, 0, args.length);

        // Handle Config commands separately
        if (cmd.equals("config") && action.equals("room")) {
            handleConfig(args);
            return;
        }

        // Determine command key
        String commandKey = "";
        if (cmd.equals("add") && action.equals("occupant")) commandKey = "add";
        else if (cmd.equals("block") && action.equals("room")) commandKey = "block";
        else if (cmd.equals("cancel") && action.equals("room")) commandKey = "cancel";
        else if (cmd.equals("status") && action.equals("room")) commandKey = "status";
        else {
            System.out.println("Unknown command: " + action);
            return;
        }

        Command command = commands.get(commandKey);
        if (command != null) command.execute(args);
        else System.out.println("Unknown command: " + cmd + " " + action);
    }

    private void handleConfig(String[] args) {
        Office office = Office.getInstance();
        try {
            if (args.length >= 2 && args[0].equalsIgnoreCase("count")) {
                int count = Integer.parseInt(args[1]);
                if (count <= 0) {
                    System.out.println("Invalid room count. Please enter a positive number.");
                    return;
                }
                office.configureRooms(count);
            } else if (args.length >= 2 && args[0].equalsIgnoreCase("max")) {
                int roomId = Integer.parseInt(args[1]);
                int capacity = Integer.parseInt(args[2]);
                if (!office.roomExists(roomId)) {
                    System.out.println("Room " + roomId + " does not exist.");
                    return;
                }
                if (capacity <= 0) {
                    System.out.println("Invalid capacity. Please enter a valid positive number.");
                    return;
                }
                office.getRoom(roomId).setMaxCapacity(capacity);
                System.out.println("Room " + roomId + " maximum capacity set to " + capacity + ".");
            } else {
                System.out.println("Invalid config command.");
            }
        } catch (Exception e) {
            System.out.println("Error in config command: " + e.getMessage());
            logger.warning("Config command failed: " + e.getMessage());
        }
    }
}
