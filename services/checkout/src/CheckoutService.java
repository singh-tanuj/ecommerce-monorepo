public class CheckoutService {

    private final PaymentService paymentService;
    private final OrderService orderService;
    private final RetryPolicy retryPolicy;

    public CheckoutService(PaymentService paymentService,
                           OrderService orderService,
                           RetryPolicy retryPolicy) {
        this.paymentService = paymentService;
        this.orderService = orderService;
        this.retryPolicy = retryPolicy;
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
