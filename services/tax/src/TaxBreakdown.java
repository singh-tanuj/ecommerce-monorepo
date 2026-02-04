package services.tax.model;

import java.util.List;

public class TaxBreakdown {

    public static class JurisdictionTaxLine {
        private final String jurisdictionCode;
        private final double rate;
        private final double taxableBase;
        private final double taxAmount;

        public JurisdictionTaxLine(
                String jurisdictionCode,
                double rate,
                double taxableBase,
                double taxAmount
        ) {
            this.jurisdictionCode = jurisdictionCode;
            this.rate = rate;
            this.taxableBase = taxableBase;
            this.taxAmount = taxAmount;
        }

        public String getJurisdictionCode() {
            return jurisdictionCode;
        }

        public double getRate() {
            return rate;
        }

        public double getTaxableBase() {
            return taxableBase;
        }

        public double getTaxAmount() {
            return taxAmount;
        }
    }

    private final List<JurisdictionTaxLine> lines;
    private final double totalTax;

    public TaxBreakdown(List<JurisdictionTaxLine> lines, double totalTax) {
        this.lines = lines;
        this.totalTax = totalTax;
    }

    public List<JurisdictionTaxLine> getLines() {
        return lines;
    }

    public double getTotalTax() {
        return totalTax;
    }
}
