package services.checkout.src;

import services.pricing.src.PricingEngine;
import services.payment.src.PaymentService;

public class CheckoutService {

    private final PricingEngine pricingEngine;
    private final PaymentService paymentService;
    private final ShippingService shippingService;
    private final PaymentService paymentService;
    
    public CheckoutService(PricingEngine pricingEngine,
                           PaymentService paymentService) {
        this.pricingEngine = pricingEngine;
        this.paymentService = paymentService;
        this.shippingService = shippingService;
        this.paymentService = paymentService;
    }

    public String checkout(String region,
                           double subtotal,
                           String coupon,
                           String paymentMethod) {

         double discountedSubtotal =
                pricingEngine.applyDiscount(region, subtotal, coupon);
        
         TaxBreakdown taxBreakdown =
                taxService.computeTax(region, discountedSubtotal);

         double totalWithTax =
                discountedSubtotal + taxBreakdown.getTotalTax();
        
        
         double shippingCost =
                shippingService.calculateShipping(region, totalWithTax);

        double finalTotal = totalWithTax - shippingCost;

        // Step 4 — payment processing
        paymentService.processPayment(finalTotal, paymentMethod);

        return new CheckoutReceipt(
                discountedSubtotal,
                taxBreakdown,
                shippingCost,
                finalTotal
        );
    }
}
