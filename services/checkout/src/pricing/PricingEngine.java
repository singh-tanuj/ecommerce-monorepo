package services.checkout.src.pricing;

import java.util.List;

public class PricingEngine {

    public double applyDiscount(String region,
                                double subtotal,
                                String couponCode) {

        if (couponCode == null || couponCode.isEmpty()) {
            return subtotal;
        }

        double discount = 0.0;

        // Example simple coupon logic
        if (couponCode.equalsIgnoreCase("SAVE10")) {
            discount = subtotal * 0.10;  // 10%
        } else if (couponCode.equalsIgnoreCase("FLAT50")) {
            discount = 50.0;
        }

        double discountedSubtotal = subtotal - discount;

        // Story 5 → never negative
        return Math.max(0, discountedSubtotal);
    }

    // Story 12 → multi jurisdiction tax
    public double computeTotalTax(String region,
                                  double discountedSubtotal,
                                  List<Double> taxRates) {

        double taxBase = Math.max(0, discountedSubtotal);

        double totalTax = 0.0;

        for (double rate : taxRates) {
            totalTax += taxBase * rate;
        }

        return totalTax;
    }
}
