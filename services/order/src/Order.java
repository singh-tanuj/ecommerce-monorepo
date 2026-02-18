package services.order.src;

public class Order {

    private double subtotal;
    private double discountedSubtotal;
    private double tax;
    private double shipping;
    private double finalTotal;

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDiscountedSubtotal() {
        return discountedSubtotal;
    }

    public void setDiscountedSubtotal(double discountedSubtotal) {
        this.discountedSubtotal = discountedSubtotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public double getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(double finalTotal) {
        this.finalTotal = finalTotal;
    }
}
