package services.tax.model;

public class TaxJurisdiction {

    private final String code;
    private final String name;
    private final double rate;

    public TaxJurisdiction(String code, String name, double rate) {
        this.code = code;
        this.name = name;
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }
}
