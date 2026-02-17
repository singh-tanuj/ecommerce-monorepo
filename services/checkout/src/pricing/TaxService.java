package services.checkout.src.pricing;

import java.util.List;
import java.util.Objects;

public class TaxService {

    private final TaxRateRepository taxRateRepository;

    public TaxService(TaxRateRepository taxRateRepository) {
        this.taxRateRepository =
                Objects.requireNonNull(taxRateRepository, "taxRateRepository must not be null");
    }

    public double computeTotalTax(String region, double discountedSubtotal) {

        // Story 5: tax base must never be negative
        double taxBase = Math.max(0, discountedSubtotal);

        List<Double> taxRates = taxRateRepository.findByRegion(region);

        double totalTax = 0.0;

        // Story 12: independent jurisdiction calculation
        for (double rate : taxRates) {
            double jurisdictionTax = taxBase * rate;
            totalTax += jurisdictionTax;
        }

        return RoundingUtil.round(totalTax);
    }
}
