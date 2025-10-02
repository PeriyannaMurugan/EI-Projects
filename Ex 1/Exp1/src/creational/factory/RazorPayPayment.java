package creational.factory;

import java.util.logging.Logger;

public class RazorPayPayment implements Payment {
    private static final Logger logger = Logger.getLogger(RazorPayPayment.class.getName());

    @Override
    public void pay(double amount) {
        logger.info("Processing RazorPay payment for amount: " + amount);
        System.out.println("Paid " + amount + " using RazorPay.");
    }
}
