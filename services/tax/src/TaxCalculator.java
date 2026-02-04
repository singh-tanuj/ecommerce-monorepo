package services.tax.src;

import services.tax.model.TaxBreakdown;
import services.tax.model.TaxJurisdiction;

import java.util.ArrayList;
import java.util.List;

public class TaxCalculator {

    public TaxBreakdown calculate(
            double taxableBase,
            List<TaxJurisdiction> jurisdictions
    ) {
        List<TaxBreakdown.JurisdictionTaxLine> lines = new ArrayList<>();
        double totalTax = 0.0;

        for (TaxJurisdiction jurisdiction : jurisdictions) {
            double taxAmount = taxableBase * jurisdiction.getRate();
            totalTax += taxAmount;

            lines.add(
                new TaxBreakdown.JurisdictionTaxLine(
                    jurisdiction.getCode(),
                    jurisdiction.getRate(),
                    taxableBase,
                    taxAmount
                )
            );
        }

        return new TaxBreakdown(lines, totalTax);
    }
}
