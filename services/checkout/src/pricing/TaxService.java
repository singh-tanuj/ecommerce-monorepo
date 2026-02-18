package services.checkout.src.pricing;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class TaxService {

    private final TaxRateRepository taxRateRepository;

    public TaxService(TaxRateRepository taxRateRepository) {
        this.taxRateRepository =
                Objects.requireNonNull(taxRateRepository);
    }

    public BigDecimal computeTotalTax(String region,
                                      BigDecimal discountedSubtotal) {

        discountedSubtotal = discountedSubtotal == null
                ? BigDecimal.ZERO
                : discountedSubtotal.max(BigDecimal.ZERO);

        List<BigDecimal> taxRates =
                taxRateRepository.findByRegion(region);

        BigDecimal totalTax = BigDecimal.ZERO;

        for (BigDecimal rate : taxRates) {
            totalTax = totalTax.add(
                    discountedSubtotal.multiply(rate));
        }

        return RoundingUtil.round(totalTax);
    }
}
