package services.checkout.src;

import services.pricing.src.PricingEngine;
import services.payment.src.PaymentService;

public class CheckoutService {

    private final PricingEngine pricingEngine;
    private final PaymentService paymentService;

    public CheckoutService(PricingEngine pricingEngine,
                           PaymentService paymentService) {
        this.pricingEngine = pricingEngine;
        this.paymentService = paymentService;
    }

    public String checkout(String region,
                           double subtotal,
                           String coupon,
                           String paymentMethod) {

        double total = pricingEngine.calculateFinalTotal(region, subtotal, coupon);

        paymentService.processPayment(total, paymentMethod);

        return "ORDER_CONFIRMED";
    }
}
