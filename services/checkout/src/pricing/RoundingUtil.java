package services.checkout.src.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundingUtil {

    private static final int SCALE = 2;

    private RoundingUtil() {}

    public static BigDecimal round(BigDecimal value) {
        return value.setScale(SCALE, RoundingMode.HALF_UP);
    }
}
