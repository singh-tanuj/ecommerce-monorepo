package services.checkout.src;

import services.pricing.src.PricingEngine;
import services.payment.src.PaymentService;

import java.util.Objects;

public class CheckoutService {

    private final PricingEngine pricingEngine;
    private final PaymentService paymentService;
    private final ShippingService shippingService;
    private final TaxService taxService;

    public CheckoutService(PricingEngine pricingEngine,
                           PaymentService paymentService,
                           ShippingService shippingService,
                           TaxService taxService) {

        this.pricingEngine =
                Objects.requireNonNull(pricingEngine, "pricingEngine must not be null");

        this.paymentService =
                Objects.requireNonNull(paymentService, "paymentService must not be null");

        this.shippingService =
                Objects.requireNonNull(shippingService, "shippingService must not be null");

        this.taxService =
                Objects.requireNonNull(taxService, "taxService must not be null");
    }

    public CheckoutReceipt checkout(String region,
                                    double subtotal,
                                    String coupon,
                                    String paymentMethod) {

        // 1️⃣ Apply discount (Story 3)
        double discountedSubtotal =
                pricingEngine.applyDiscount(region, subtotal, coupon);

        // 2️⃣ Compute tax on discounted subtotal (Story 5 + 12)
        TaxBreakdown taxBreakdown =
                taxService.computeTax(region, discountedSubtotal);

        double totalWithTax =
                discountedSubtotal + taxBreakdown.getTotalTax();

        // 3️⃣ Shipping computed after discount (Story 10 alignment)
        double shippingCost =
                shippingService.calculateShipping(region, discountedSubtotal);

        // 4️⃣ Final total
        double finalTotal =
                totalWithTax + shippingCost;

        // 5️⃣ Payment processing
        paymentService.processPayment(finalTotal, paymentMethod);

        return new CheckoutReceipt(
                discountedSubtotal,
                taxBreakdown,
                shippingCost,
                finalTotal
        );
    }
}
