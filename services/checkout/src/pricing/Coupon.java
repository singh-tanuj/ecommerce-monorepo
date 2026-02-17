package services.checkout.src.pricing;

import java.util.Set;

public class Coupon {

    public enum Type {
        PERCENTAGE,
        FIXED
    }

    private final String code;
    private final Type type;
    private final double value;        // 10 means 10% if percentage
    private final double minSubtotal;
    private final Set<String> excludedSkus;

    public Coupon(String code,
                  Type type,
                  double value,
                  double minSubtotal,
                  Set<String> excludedSkus) {

        this.code = code;
        this.type = type;
        this.value = value;
        this.minSubtotal = minSubtotal;
        this.excludedSkus = excludedSkus;
    }

    public String getCode() {
        return code;
    }

    public Type getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public double getMinSubtotal() {
        return minSubtotal;
    }

    public Set<String> getExcludedSkus() {
        return excludedSkus;
    }

    public boolean isEligible(double subtotal) {
        return subtotal >= minSubtotal;
    }

    // 🔧 Improvement: Guard for excluded SKUs
    public boolean isSkuExcluded(String sku) {
        return excludedSkus != null && excludedSkus.contains(sku);
    }
}
