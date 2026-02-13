package services.checkout.src;

import java.util.Objects;

public class CheckoutOrchestrator {

    private final CartPort cartPort;
    private final CheckoutService checkoutService;
    private final ShippingPort shippingPort;

    public CheckoutOrchestrator(
            CartPort cartPort,
            CheckoutService checkoutService,
            ShippingPort shippingPort
    ) {
        this.cartPort = Objects.requireNonNull(cartPort, "cartPort must not be null");
        this.checkoutService = Objects.requireNonNull(checkoutService, "checkoutService must not be null");
        this.shippingPort = Objects.requireNonNull(shippingPort, "shippingPort must not be null");
    }

    public CheckoutReceipt placeOrder(
            String cartId,
            String userId,
            String regionCode,
            double discount
    ) {

        // 1️⃣ Fetch subtotal from cart domain
        double subtotal = cartPort.getSubtotal(cartId);

        // 2️⃣ Calculate shipping
        double shipping = shippingPort.calculateShipping(subtotal);

        // 3️⃣ Compute final total using multi-jurisdiction tax logic
        double finalTotal = checkoutService.computeFinalTotal(
                regionCode,
                subtotal,
                discount,
                shipping
        );

        return new CheckoutReceipt(
                cartId,
                userId,
                finalTotal
        );
    }

    // -----------------------
    // PORT DEFINITIONS
    // -----------------------

    public interface CartPort {
        double getSubtotal(String cartId);
    }

    public interface ShippingPort {
        double calculateShipping(double subtotal);
    }

    public static class CheckoutReceipt {
        private final String cartId;
        private final String userId;
        private final double totalAmount;

        public CheckoutReceipt(String cartId, String userId, double totalAmount) {
            this.cartId = cartId;
            this.userId = userId;
            this.totalAmount = totalAmount;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public String getCartId() {
            return cartId;
        }

        public String getUserId() {
            return userId;
        }
    }
}
