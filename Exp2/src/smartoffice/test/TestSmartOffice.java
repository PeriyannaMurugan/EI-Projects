package smartoffice.test;

import smartoffice.command.CommandRegistry;

public class TestSmartOffice {
    public static void main(String[] args) {
        CommandRegistry registry = new CommandRegistry();

        System.out.println("=== Smart Office Test Cases ===");

        // Positive Cases
        System.out.println("\n-- Positive Cases --");
        registry.executeCommand("Config room count 3");         // Configure 3 rooms
        registry.executeCommand("Config room max 1 10");       // Max capacity 10
        registry.executeCommand("Add occupant 1 2");           // Room 1 occupied by 2
        registry.executeCommand("Block room 1 09:00 60");      // Room 1 booked
        registry.executeCommand("Cancel room 1");              // Booking cancelled
        registry.executeCommand("Add occupant 1 0");           // Room 1 unoccupied

        // Negative Cases
        System.out.println("\n-- Negative Cases --");
        registry.executeCommand("Add occupant 4 2");           // Non-existent room
        registry.executeCommand("Block room A 09:00 60");      // Invalid room number
        registry.executeCommand("Config room max 1 -5");       // Invalid capacity
        registry.executeCommand("Status room 1");              // Room unoccupied >5 mins
        registry.executeCommand("Block room 1 09:00 60");      // Book room 1 again
        registry.executeCommand("Block room 1 09:00 60");      // Already booked
        registry.executeCommand("Cancel room 2");              // Room 2 not booked
        registry.executeCommand("Add occupant 2 1");           // Occupancy insufficient

        System.out.println("\n=== Test Cases Completed ===");
    }
}
