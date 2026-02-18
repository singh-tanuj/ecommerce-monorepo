package services.checkout.src.pricing;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaxRateRepository {

    public List<Double> findByRegion(String region) {

        if (region == null) {
            throw new IllegalArgumentException("Region cannot be null");
        }

        switch (region) {
            case "US-CA":
                return Arrays.asList(0.06, 0.02);
            case "US-NY":
                return Arrays.asList(0.04, 0.03);
            case "EU-DE":
                return Collections.singletonList(0.19);
            default:
                // Fallback to default tax rate
                return Collections.singletonList(0.05);
        }
    }
}
