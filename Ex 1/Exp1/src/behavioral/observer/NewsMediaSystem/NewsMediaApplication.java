package behavioral.observer.NewsMediaSystem;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.ConsoleHandler;
import java.util.logging.SimpleFormatter;

public class NewsMediaApplication {
    private static final Logger logger = Logger.getLogger(NewsMediaApplication.class.getName());
    private NewsChannel newsChannel;
    private Scanner scanner;
    private boolean isRunning;
    
    public NewsMediaApplication() {
        setupLogging();
        this.newsChannel = new NewsChannel();
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        logger.info("NewsMediaApplication initialized");
    }
    
    private void setupLogging() {
        Logger rootLogger = Logger.getLogger("");
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        rootLogger.addHandler(handler);
        rootLogger.setLevel(Level.INFO);
    }
    
    public void start() {
        System.out.println("\n=== NEWS MEDIA OBSERVER PATTERN DEMO ===");
        System.out.println("Welcome to the News Channel Management System!");
        
        addDefaultSubscribers();
        
        while (isRunning) {
            try {
                showMenu();
                int choice = getValidChoice();
                handleMenuChoice(choice);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error in main loop", e);
                System.out.println("An error occurred. Please try again.");
            }
        }
        
        cleanup();
    }
    
    private void addDefaultSubscribers() {
        newsChannel.registerObserver(new NewsSubscriber("John Doe", "mobile"));
        newsChannel.registerObserver(new NewsSubscriber("jane.smith@email.com", "email"));
        newsChannel.registerObserver(new NewsSubscriber("NewsPortal.com", "website"));
        System.out.println("Default subscribers added successfully.");
    }
    
    private void showMenu() {
        System.out.println("\n--- NEWS CHANNEL MENU ---");
        System.out.println("1. Publish News");
        System.out.println("2. Add Subscriber");
        System.out.println("3. Remove Subscriber");
        System.out.println("4. View Subscriber Count");
        System.out.println("5. View Latest News");
        System.out.println("6. Exit");
        System.out.print("Enter your choice (1-6): ");
    }
    
    private int getValidChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice >= 1 && choice <= 6) {
                return choice;
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                return getValidChoice();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return getValidChoice();
        }
    }
    
    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                publishNews();
                break;
            case 2:
                addSubscriber();
                break;
            case 3:
                removeSubscriber();
                break;
            case 4:
                viewSubscriberCount();
                break;
            case 5:
                viewLatestNews();
                break;
            case 6:
                exitApplication();
                break;
        }
    }
    
    private void publishNews() {
        System.out.print("Enter news to publish: ");
        String news = scanner.nextLine().trim();
        
        if (news.isEmpty()) {
            System.out.println("News cannot be empty.");
            return;
        }
        
        newsChannel.setNews(news);
        System.out.println("News published successfully!");
    }
    
    private void addSubscriber() {
        System.out.print("Enter subscriber name: ");
        String name = scanner.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("Subscriber name cannot be empty.");
            return;
        }
        
        System.out.print("Enter subscriber type (mobile/email/website): ");
        String type = scanner.nextLine().trim();
        
        if (type.isEmpty()) {
            type = "general";
        }
        
        NewsSubscriber subscriber = new NewsSubscriber(name, type);
        newsChannel.registerObserver(subscriber);
        System.out.println("Subscriber added successfully!");
    }
    
    private void removeSubscriber() {
        System.out.println("Note: This is a simplified demo. In a real application,");
        System.out.println("you would have a list of subscribers to choose from.");
        System.out.println("Functionality can be extended to maintain subscriber references.");
    }
    
    private void viewSubscriberCount() {
        int count = newsChannel.getObserverCount();
        System.out.println("Total subscribers: " + count);
    }
    
    private void viewLatestNews() {
        String latest = newsChannel.getLatestNews();
        if (latest != null && !latest.isEmpty()) {
            System.out.println("Latest news: " + latest);
        } else {
            System.out.println("No news published yet.");
        }
    }
    
    private void exitApplication() {
        System.out.println("Thank you for using News Media System!");
        isRunning = false;
    }
    
    private void cleanup() {
        scanner.close();
        logger.info("Application cleaned up successfully");
    }
    
    public static void main(String[] args) {
        NewsMediaApplication app = new NewsMediaApplication();
        app.start();
    }
}
