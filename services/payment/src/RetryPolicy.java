public class RetryPolicy {

    private final int maxAttempts;
    private final long baseDelayMillis;

    public RetryPolicy(int maxAttempts, long baseDelayMillis) {
        this.maxAttempts = maxAttempts;
        this.baseDelayMillis = baseDelayMillis;
    }

    public <T> T run(java.util.concurrent.Callable<T> action) {
        int attempt = 0;
        while (true) {
            try {
                attempt++;
                return action.call();
            } catch (PaymentFailedException e) {
                if (attempt >= maxAttempts) throw e;
                sleep(backoff(attempt));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private long backoff(int attempt) {
        // exponential-ish: baseDelay * 2^(attempt-1)
        return baseDelayMillis * (1L << (attempt - 1));
    }

    private void sleep(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
    }
}
