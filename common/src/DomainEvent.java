package common.src;

import java.time.Instant;

public abstract class DomainEvent {

    private final String eventId;
    private final Instant occurredAt;

    protected DomainEvent(String eventId) {
        this.eventId = eventId;
        this.occurredAt = Instant.now();
    }

    public String getEventId() {
        return eventId;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    public abstract String eventType();
}
