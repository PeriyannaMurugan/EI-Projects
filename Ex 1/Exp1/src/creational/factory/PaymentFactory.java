package creational.factory;

import java.util.logging.Logger;

public class PaymentFactory {
    private static final Logger logger = Logger.getLogger(PaymentFactory.class.getName());

    public static Payment getPaymentMethod(String type) {
        if (type == null) {
            logger.severe("Payment type is null");
            throw new IllegalArgumentException("Payment type cannot be null.");
        }

        switch (type.toLowerCase()) {
            case "paypal":
                logger.info("Selected PayPal as payment method.");
                return new PayPalPayment();
            case "stripe":
                logger.info("Selected Stripe as payment method.");
                return new StripePayment();
            case "razorpay":
                logger.info("Selected RazorPay as payment method.");
                return new RazorPayPayment();
            default:
                logger.warning("Unknown payment type requested: " + type);
                throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }
}
