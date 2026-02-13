package common.src;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class ClockProvider {

    private final Clock clock;

    public ClockProvider() {
        this.clock = Clock.systemUTC();
    }

    public ClockProvider(Clock clock) {
        this.clock = clock;
    }

    public Instant now() {
        return Instant.now(clock);
    }

    public ZoneId getZone() {
        return clock.getZone();
    }
}
