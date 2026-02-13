package services.shipping.src;

public class ShippingService {

    public double calculateShipping(double subtotal) {
        if (subtotal > 100) return 0;
        return 7.99;
    }
}
