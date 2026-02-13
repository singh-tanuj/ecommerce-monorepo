package services.promotions.src;

import java.util.HashSet;
import java.util.Set;

public class CouponRepository {

    private final Set<String> validCoupons = new HashSet<>();

    public CouponRepository() {
        validCoupons.add("SAVE10");
    }

    public boolean exists(String code) {
        return validCoupons.contains(code);
    }
}
