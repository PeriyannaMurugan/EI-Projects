package creational.factory;

import java.util.logging.Logger;

public class StripePayment implements Payment {
    private static final Logger logger = Logger.getLogger(StripePayment.class.getName());

    @Override
    public void pay(double amount) {
        logger.info("Processing Stripe payment for amount: " + amount);
        System.out.println("Paid " + amount + " using Stripe.");
    }
}
