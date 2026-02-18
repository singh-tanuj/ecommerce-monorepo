package services.checkout.src.pricing;

import java.util.Set;

public class Coupon {

    public enum Type {
        PERCENTAGE,
        FIXED
    }

    private final String code;
    private final Type type;
    private final double value;
    private final double minSubtotal;
    private final Set<String> excludedSkus;

    public Coupon(String code,
                  Type type,
                  double value,
                  double minSubtotal,
                  Set<String> excludedSkus) {

        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Coupon code cannot be empty");
        }

        if (value < 0) {
            throw new IllegalArgumentException("Coupon value cannot be negative");
        }

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

    public boolean isSkuExcluded(String sku) {
        return excludedSkus != null && excludedSkus.contains(sku);
    }

    public boolean isPercentage() {
        return this.type == Type.PERCENTAGE;
    }
}
