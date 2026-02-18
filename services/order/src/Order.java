package services.order.src;

public class Order {

    private double subtotal;
    private double discountedSubtotal;
    private double tax;
    private double shipping;
    private double finalTotal;

    private String status;

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = Math.max(0, subtotal);
    }

    public double getDiscountedSubtotal() {
        return discountedSubtotal;
    }

    public void setDiscountedSubtotal(double discountedSubtotal) {
        this.discountedSubtotal = Math.max(0, discountedSubtotal);
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = Math.max(0, tax);
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = Math.max(0, shipping);
    }

    public double getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(double finalTotal) {

        if (finalTotal < 0) {
            throw new IllegalArgumentException("Final total cannot be negative");
        }

        this.finalTotal = finalTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty");
        }

        this.status = status;
    }

    public boolean isPaid() {
        return "PAID".equalsIgnoreCase(this.status);
    }

    // 🔥 NEW METHOD (functional addition)
    public boolean isValidTotal() {
        double calculated = discountedSubtotal + tax + shipping;
        return Math.abs(calculated - finalTotal) < 0.01;
    }
}
