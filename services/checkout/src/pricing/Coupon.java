package services.checkout.src.pricing;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class Coupon {

    public enum Type {
        PERCENTAGE,
        FIXED
    }

    private final String code;
    private final Type type;
    private final BigDecimal value;
    private final BigDecimal minSubtotal;
    private final Set<String> excludedSkus;

    public Coupon(String code,
                  Type type,
                  BigDecimal value,
                  BigDecimal minSubtotal,
                  Set<String> excludedSkus) {

        this.code = Objects.requireNonNull(code, "code must not be null");
        this.type = Objects.requireNonNull(type, "type must not be null");
        this.value = Objects.requireNonNull(value, "value must not be null");
        this.minSubtotal = Objects.requireNonNull(minSubtotal, "minSubtotal must not be null");
        this.excludedSkus = excludedSkus == null
                ? Collections.emptySet()
                : Collections.unmodifiableSet(excludedSkus);
    }

    public String getCode() { return code; }
    public Type getType() { return type; }
    public BigDecimal getValue() { return value; }
    public BigDecimal getMinSubtotal() { return minSubtotal; }
    public Set<String> getExcludedSkus() { return excludedSkus; }

    public boolean isEligible(BigDecimal subtotal) {
        return subtotal.compareTo(minSubtotal) >= 0;
    }

    public boolean isSkuExcluded(String sku) {
        return excludedSkus.contains(sku);
    }
}
