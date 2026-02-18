package services.checkout.src.pricing;

import java.util.Objects;

public class PricingCalculator {

    private final TaxService taxService;

    public PricingCalculator(TaxService taxService) {
        this.taxService = Objects.requireNonNull(
                taxService, "taxService must not be null"
        );
    }

    public double applyCoupon(double subtotal, Coupon coupon) {

        if (coupon == null) {
            return RoundingUtil.round(subtotal);
        }

        CouponValidator.validate(coupon, subtotal);

        double discount;

        if (coupon.isPercentage()) {
            discount = subtotal * (coupon.getValue() / 100.0);
        } else {
            discount = coupon.getValue();
        }

        double discountedSubtotal = safeSubtract(subtotal, discount);

        return RoundingUtil.round(discountedSubtotal);
    }

    public double computeTax(String region, double discountedSubtotal) {
        return taxService.computeTotalTax(region, discountedSubtotal);
    }

    public double computeFinalTotal(double discountedSubtotal,
                                    double totalTax,
                                    double shippingCost) {

        double finalTotal = discountedSubtotal + totalTax + shippingCost;

        return RoundingUtil.round(Math.max(0, finalTotal));
    }

    private double safeSubtract(double a, double b) {
        return Math.max(0, a - b);
    }
}
