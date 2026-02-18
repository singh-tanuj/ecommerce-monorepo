package services.checkout.src.pricing;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class PricingEngine {

    public BigDecimal applyDiscount(BigDecimal subtotal, String couponCode) {

        if (subtotal == null) {
            throw new IllegalArgumentException("Subtotal cannot be null");
        }

        if (couponCode == null || couponCode.trim().isEmpty()) {
            return RoundingUtil.round(subtotal);
        }

        couponCode = couponCode.trim().toUpperCase();

        BigDecimal discount = BigDecimal.ZERO;

        switch (couponCode) {
            case "SAVE10":
                discount = subtotal.multiply(BigDecimal.valueOf(0.10));
                break;
            case "FLAT50":
                discount = BigDecimal.valueOf(50);
                break;
        }

        discount = discount.min(subtotal);

        return RoundingUtil.round(subtotal.subtract(discount));
    }

    public BigDecimal computeTotalTax(BigDecimal subtotal,
                                      List<BigDecimal> taxRates) {

        Objects.requireNonNull(taxRates, "taxRates cannot be null");

        subtotal = subtotal.max(BigDecimal.ZERO);

        BigDecimal totalTax = BigDecimal.ZERO;

        for (BigDecimal rate : taxRates) {
            totalTax = totalTax.add(subtotal.multiply(rate));
        }

        return RoundingUtil.round(totalTax);
    }
}
