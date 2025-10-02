package creational.factory;

import java.util.logging.Logger;

public class PayPalPayment implements Payment {
    private static final Logger logger = Logger.getLogger(PayPalPayment.class.getName());

    @Override
    public void pay(double amount) {
        logger.info("Processing PayPal payment for amount: " + amount);
        System.out.println("Paid " + amount + " using PayPal.");
    }
}
