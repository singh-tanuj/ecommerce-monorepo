package services.promotions.src;

public class CouponService {

    private final CouponRepository repository;

    public CouponService(CouponRepository repository) {
        this.repository = repository;
    }

    public boolean isValid(String code) {
        return repository.exists(code);
    }
}
