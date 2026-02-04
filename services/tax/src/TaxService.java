package services.tax.src;

import services.tax.model.TaxBreakdown;
import services.tax.model.TaxJurisdiction;

import java.util.List;

public class TaxService {

    private final TaxJurisdictionResolver resolver = new TaxJurisdictionResolver();
    private final TaxCalculator calculator = new TaxCalculator();

    public TaxBreakdown computeTax(
            String regionCode,
            double subtotal,
            double discount
    ) {
        // Business rule: discount reduces tax base
        double taxableBase = Math.max(0.0, subtotal - discount);

        List<TaxJurisdiction> jurisdictions =
                resolver.resolve(regionCode);

        return calculator.calculate(taxableBase, jurisdictions);
    }
}
