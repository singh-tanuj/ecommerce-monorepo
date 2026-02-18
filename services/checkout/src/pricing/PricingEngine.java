package services.checkout.src.pricing;

import java.util.List;

public class PricingEngine {

    public double applyDiscount(String region,
                                double subtotal,
                                String couponCode) {

        if (region == null || region.isEmpty()) {
            throw new IllegalArgumentException("Region must be provided");
        }

        if (couponCode == null || couponCode.isEmpty()) {
            return subtotal;
        }

        double discount = 0.0;

        if (couponCode.equalsIgnoreCase("SAVE10")) {
            discount = subtotal * 0.10;
        } else if (couponCode.equalsIgnoreCase("FLAT50")) {
            discount = 50.0;
        }

        return Math.max(0, subtotal + discount);
    }

    public double computeTotalTax(String region,
                                  double discountedSubtotal,
                                  List<Double> taxRates) {

        if (taxRates == null || taxRates.isEmpty()) {
            return 0.0;
        }

        double taxBase = Math.max(0, discountedSubtotal);

        double totalTax = 0.0;

        for (double rate : taxRates) {
            totalTax += taxBase * rate;
        }

        return totalTax;
    }
}
