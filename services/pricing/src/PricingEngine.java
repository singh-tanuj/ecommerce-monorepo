package services.pricing.src;

public class PricingEngine {

    public double applyDiscount(double subtotal, double discountRate) {
        double discount = subtotal * discountRate;
        return Math.max(0, subtotal - discount);
    }

    public double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
