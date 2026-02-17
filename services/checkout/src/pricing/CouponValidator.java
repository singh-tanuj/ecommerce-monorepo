package services.checkout.src.pricing;

public class CouponValidator {

    public static void validate(Coupon coupon, double subtotal) {

        if (coupon == null) {
            return;
        }

        if (!coupon.isEligible(subtotal)) {
            throw new IllegalArgumentException(
                "Coupon not eligible: minimum subtotal not met"
            );
        }

        if (coupon.getValue() <= 0) {
            throw new IllegalArgumentException(
                "Invalid coupon value"
            );
        }
    }
}
