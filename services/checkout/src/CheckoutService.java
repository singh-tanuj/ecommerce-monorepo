package services.checkout.src;

import services.pricing.src.PricingEngine;
import services.payment.src.PaymentService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CheckoutService {

    private final PricingEngine pricingEngine;
    private final PaymentService paymentService;
    private final ShippingService shippingService;

    public CheckoutService(PricingEngine pricingEngine,
                           PaymentService paymentService,
                           ShippingService shippingService) {

        this.pricingEngine =
                Objects.requireNonNull(pricingEngine);

        this.paymentService =
                Objects.requireNonNull(paymentService);

        this.shippingService =
                Objects.requireNonNull(shippingService);
    }

    public CheckoutReceipt checkout(String region,
                                    double subtotal,
                                    String coupon,
                                    String paymentMethod) {

        // 1️⃣ Apply discount (Story 3)
        double discountedSubtotal =
                pricingEngine.applyDiscount(region, subtotal, coupon);

        // 2️⃣ Multi-jurisdiction tax (Story 12)
        List<Double> taxRates = getTaxRates(region);

        double totalTax =
                pricingEngine.computeTotalTax(region,
                        discountedSubtotal,
                        taxRates);

        double totalWithTax =
                discountedSubtotal + totalTax;

        // 3️⃣ Shipping AFTER discount (corrected)
        double shippingCost =
                shippingService.calculateShipping(region,
                        discountedSubtotal);

        double finalTotal =
                totalWithTax + shippingCost;

        // 4️⃣ Payment
        paymentService.processPayment(finalTotal, paymentMethod);

        return new CheckoutReceipt(
                discountedSubtotal,
                totalTax,
                shippingCost,
                finalTotal
        );
    }

    private List<Double> getTaxRates(String region) {

        if (region.equals("US-CA")) {
            return Arrays.asList(0.06, 0.02);
        }

        if (region.equals("US-NY")) {
            return Arrays.asList(0.04, 0.03);
        }

        return Arrays.asList(0.05);
    }
}
