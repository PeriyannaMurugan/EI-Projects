package creational.factory;

import java.util.Scanner;
import java.util.logging.*;

public class FactoryDemo {
    private static final Logger logger = Logger.getLogger(FactoryDemo.class.getName());

    public static void main(String[] args) {
        setupLogger();

        Scanner sc = new Scanner(System.in);
        System.out.println("Select Payment Method (paypal / stripe / razorpay): ");
        String method = sc.nextLine();

        System.out.print("Enter amount to pay: ");
        double amount = sc.nextDouble();

        try {
            Payment payment = PaymentFactory.getPaymentMethod(method);
            payment.pay(amount);
            logger.info("Payment of " + amount + " completed successfully with " + method);
        } catch (IllegalArgumentException e) {
            logger.severe("Payment failed: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    // Configure logging to both console and file
    private static void setupLogger() {
        try {
            LogManager.getLogManager().reset();
            Logger rootLogger = Logger.getLogger("");

            // Console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            rootLogger.addHandler(consoleHandler);

            // File handler
            FileHandler fileHandler = new FileHandler("payment.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            rootLogger.addHandler(fileHandler);

            rootLogger.setLevel(Level.INFO);
        } catch (Exception e) {
            System.err.println("Failed to setup logger: " + e.getMessage());
        }
    }
}
