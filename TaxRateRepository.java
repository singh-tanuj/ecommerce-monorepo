package services.checkout.src.pricing;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaxRateRepository {

    public List<Double> findByRegion(String region) {

        switch (region) {
            case "US-CA":
                return Arrays.asList(0.06, 0.02); // State + Local
            case "US-NY":
                return Arrays.asList(0.04, 0.03); // State + City
            case "EU-DE":
                return Collections.singletonList(0.19);
            default:
                return Collections.singletonList(0.05);
        }
    }
}
