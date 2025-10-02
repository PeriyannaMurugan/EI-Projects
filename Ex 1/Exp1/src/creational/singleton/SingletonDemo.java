package creational.singleton;

import java.util.Scanner;
import java.util.logging.*;

public class SingletonDemo {
    private static final Logger logger = Logger.getLogger(SingletonDemo.class.getName());

    public static void main(String[] args) {
        setupLogger();

        Scanner sc = new Scanner(System.in);
        EducationManager manager = EducationManager.getInstance();
        manager.displayInstitute();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Education Manager ---");
            System.out.println("1. Enroll Student");
            System.out.println("2. Hire Faculty");
            System.out.println("3. Register Parent");
            System.out.println("4. Show All Students");
            System.out.println("5. Show All Faculties");
            System.out.println("6. Show All Parents");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String sName = sc.nextLine();
                    System.out.print("Enter course: ");
                    String course = sc.nextLine();
                    manager.enrollStudent(new Student(sName, course));
                    break;
                case 2:
                    System.out.print("Enter faculty name: ");
                    String fName = sc.nextLine();
                    System.out.print("Enter subject: ");
                    String subject = sc.nextLine();
                    manager.hireFaculty(new Faculty(fName, subject));
                    break;
                case 3:
                    System.out.print("Enter parent name: ");
                    String pName = sc.nextLine();
                    System.out.print("Enter student name: ");
                    String child = sc.nextLine();
                    manager.registerParent(new Parent(pName, child));
                    break;
                case 4:
                    manager.showAllStudents();
                    break;
                case 5:
                    manager.showAllFaculties();
                    break;
                case 6:
                    manager.showAllParents();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        sc.close();
    }

    private static void setupLogger() {
        try {
            LogManager.getLogManager().reset();
            Logger rootLogger = Logger.getLogger("");

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            rootLogger.addHandler(consoleHandler);

            FileHandler fileHandler = new FileHandler("education.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            rootLogger.addHandler(fileHandler);

            rootLogger.setLevel(Level.INFO);
        } catch (Exception e) {
            System.err.println("Failed to setup logger: " + e.getMessage());
        }
    }
}
