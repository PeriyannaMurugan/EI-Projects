package smartoffice;

import smartoffice.command.CommandRegistry;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommandRegistry registry = new CommandRegistry();
        Scanner sc = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("      Welcome to Smart Office System     ");
        System.out.println("========================================");
        System.out.println("Available Commands:");
        System.out.println("  config room count <number>");
        System.out.println("  config room max <room_id> <capacity>");
        System.out.println("  add occupant <room_id> <count>");
        System.out.println("  block room <room_id> <HH:MM> <duration>");
        System.out.println("  cancel room <room_id>");
        System.out.println("  status room <room_id>");
        System.out.println("  exit");
        System.out.println("----------------------------------------");

        while (true) {
            System.out.print("Enter command: ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting Smart Office System. Goodbye!");
                break;
            }
            registry.executeCommand(input);
            System.out.println("----------------------------------------");
        }

        sc.close();
    }
}
