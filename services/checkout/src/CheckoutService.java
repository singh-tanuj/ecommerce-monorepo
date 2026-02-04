import services.tax.model.TaxBreakdown;
import services.tax.src.TaxService;


public class CheckoutService {

    private final PaymentService paymentService;
    private final OrderService orderService;
    private final RetryPolicy retryPolicy;
    private final TaxService taxService;


private final TaxService taxService;

    }
    // ECOM-12: Support multiple concurrent tax jurisdictions

public double computeFinalTotal(String regionCode,
                                double subtotal,
                                double discount,
                                double shipping) {

    // Discount reduces tax base consistently
    double taxableBase = Math.max(0.0, subtotal - discount);

    // Multi-jurisdiction tax breakdown
    TaxBreakdown breakdown = taxService.computeTax(regionCode, subtotal, discount);

    // Final total = discounted subtotal + summed tax + shipping
    return taxableBase + breakdown.getTotalTax() + shipping;
}

    public void checkout(String orderId, PaymentRequest request) {
        try {
            retryPolicy.run(() -> {
                paymentService.processPayment(request);
                return null;
            });
        } catch (PaymentFailedException e) {
            // retries exhausted OR non-retryable failure bubbled up
            orderService.markPaymentFailed(orderId);
            throw new CheckoutException("Payment failed after retries", e);
        }
    }
}
