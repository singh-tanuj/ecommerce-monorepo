package services.checkout.src.pricing;

import java.math.BigDecimal;

public class CouponValidator {

    public static void validate(Coupon coupon, BigDecimal subtotal) {

        if (coupon == null) return;

        if (coupon.getValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Coupon value must be positive");
        }

        if (!coupon.isEligible(subtotal)) {
            throw new IllegalArgumentException(
                    "Coupon not eligible: minimum subtotal not met");
        }
    }
}
