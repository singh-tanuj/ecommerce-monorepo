package services.pricing.src;

public class PricingBreakdown {

    private final double subtotal;
    private final double discount;
    private final double tax;
    private final double shipping;
    private final double total;

    public PricingBreakdown(
            double subtotal,
            double discount,
            double tax,
            double shipping,
            double total
    ) {
        this.subtotal = subtotal;
        this.discount = discount;
        this.tax = tax;
        this.shipping = shipping;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }
}
