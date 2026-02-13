package common.src;

import java.util.UUID;

public final class CorrelationId {

    private CorrelationId() {}

    public static String generate() {
        return UUID.randomUUID().toString();
    }

    public static boolean isValid(String id) {
        try {
            UUID.fromString(id);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
