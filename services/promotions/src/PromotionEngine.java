package services.promotions.src;

public class PromotionEngine {

    public boolean isCouponApplicable(String couponCode, double subtotal) {
        return couponCode != null &&
               couponCode.startsWith("SAVE") &&
               subtotal > 50;
    }

    public double computeDiscount(double subtotal, String couponCode) {
        if (!isCouponApplicable(couponCode, subtotal)) return 0;
        return subtotal * 0.10;
    }
}
