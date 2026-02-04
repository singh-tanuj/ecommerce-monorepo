package services.tax.src;

import services.tax.model.TaxJurisdiction;

import java.util.Arrays;
import java.util.List;

public class TaxJurisdictionResolver {

    /**
     * Example regionCode: "CA-SF"
     */
    public List<TaxJurisdiction> resolve(String regionCode) {

        if ("CA-SF".equalsIgnoreCase(regionCode)) {
            return Arrays.asList(
                new TaxJurisdiction("STATE_CA", "California State Tax", 0.0625),
                new TaxJurisdiction("LOCAL_SF", "San Francisco Local Tax", 0.0125)
            );
        }

        // Default fallback
        return Arrays.asList(
            new TaxJurisdiction("DEFAULT", "Default Tax", 0.05)
        );
    }
}
