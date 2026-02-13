package services.refund.src;

public class RefundService {

    private final RefundCalculator calculator;

    public RefundService(RefundCalculator calculator) {
        this.calculator = calculator;
    }

    public double calculateRefund(double originalAmount, double refundedPortion) {
        return calculator.compute(originalAmount, refundedPortion);
    }
}
