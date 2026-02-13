package services.pricing.src;

import java.util.List;

public class DiscountAllocator {

    public double allocateProportionally(double totalDiscount, List<Double> lineTotals) {
        double sum = lineTotals.stream().mapToDouble(Double::doubleValue).sum();
        if (sum == 0) return 0;

        double allocated = 0;
        for (double line : lineTotals) {
            allocated += (line / sum) * totalDiscount;
        }
        return allocated;
    }
}
