package services.checkout.src.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class PricingCalculator {

    private final TaxService taxService;

    public PricingCalculator(TaxService taxService) {
        this.taxService = Objects.requireNonNull(taxService);
    }

    public BigDecimal applyCoupon(BigDecimal subtotal, Coupon coupon) {

        if (subtotal == null) {
            throw new IllegalArgumentException("Subtotal cannot be null");
        }

        subtotal = subtotal.max(BigDecimal.ZERO);

        if (coupon == null) {
            return RoundingUtil.round(subtotal);
        }

        CouponValidator.validate(coupon, subtotal);

        BigDecimal discount;

        if (coupon.getType() == Coupon.Type.PERCENTAGE) {
            discount = subtotal.multiply(
                    coupon.getValue().divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP));
        } else {
            discount = coupon.getValue();
        }

        // Prevent discount overflow
        discount = discount.min(subtotal);

        BigDecimal discountedSubtotal = subtotal.subtract(discount);

        return RoundingUtil.round(discountedSubtotal.max(BigDecimal.ZERO));
    }

    public BigDecimal computeTax(String region, BigDecimal discountedSubtotal) {
        return taxService.computeTotalTax(region, discountedSubtotal);
    }

    public BigDecimal computeFinalTotal(BigDecimal discountedSubtotal,
                                        BigDecimal totalTax,
                                        BigDecimal shippingCost) {

        shippingCost = shippingCost == null ? BigDecimal.ZERO : shippingCost.max(BigDecimal.ZERO);

        BigDecimal finalTotal = discountedSubtotal
                .add(totalTax)
                .add(shippingCost);

        return RoundingUtil.round(finalTotal.max(BigDecimal.ZERO));
    }
}
