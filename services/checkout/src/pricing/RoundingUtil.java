package services.checkout.src.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundingUtil {

    private static final int SCALE = 2;

    private RoundingUtil() {}

    public static double round(double value) {
        return BigDecimal
                .valueOf(value)
                .setScale(SCALE, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
