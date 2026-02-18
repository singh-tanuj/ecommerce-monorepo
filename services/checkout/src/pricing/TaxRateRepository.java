package services.checkout.src.pricing;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class TaxRateRepository {

    public List<BigDecimal> findByRegion(String region) {

        if (region == null) {
            return Collections.singletonList(BigDecimal.valueOf(0.05));
        }

        switch (region.toUpperCase()) {
            case "US-CA":
                return List.of(
                        BigDecimal.valueOf(0.06),
                        BigDecimal.valueOf(0.02));
            case "US-NY":
                return List.of(
                        BigDecimal.valueOf(0.04),
                        BigDecimal.valueOf(0.03));
            case "EU-DE":
                return List.of(BigDecimal.valueOf(0.19));
            default:
                return List.of(BigDecimal.valueOf(0.05));
        }
    }
}
