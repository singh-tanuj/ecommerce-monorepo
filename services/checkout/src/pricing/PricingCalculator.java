package services.checkout.src.pricing;

import java.util.Objects;

public class PricingCalculator {

    private final TaxService taxService;

    public PricingCalculator(TaxService taxService) {
        // 🔧 Improvement: Null safety
        this.taxService =
                Objects.requireNonNull(taxService, "taxService must not be null");
    }

    /**
     * Applies coupon discount (Story 3)
     */
    public double applyCoupon(double subtotal, Coupon coupon) {

        if (coupon == null) {
            return RoundingUtil.round(subtotal);
        }

        CouponValidator.validate(coupon, subtotal);

        double discount;

        // 🔧 Improvement: Percentage divided by 100
        if (coupon.getType() == Coupon.Type.PERCENTAGE) {
            discount = subtotal * (coupon.getValue() / 100.0);
        } else {
            discount = coupon.getValue();
        }

        double discountedSubtotal = subtotal - discount;

        // Story 3: discounted subtotal must never go below zero
        return RoundingUtil.round(Math.max(0, discountedSubtotal));
    }

    /**
     * Computes tax (Story 5 + Story 12)
     */
    public double computeTax(String region, double discountedSubtotal) {
        return taxService.computeTotalTax(region, discountedSubtotal);
    }

    /**
     * Final total calculation
     */
    public double computeFinalTotal(double discountedSubtotal,
                                    double totalTax,
                                    double shippingCost) {

        double finalTotal = discountedSubtotal + totalTax + shippingCost;

        return RoundingUtil.round(Math.max(0, finalTotal));
    }
}
