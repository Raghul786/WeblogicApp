package com.example.reddit.usecase;

public class HealthStatus {

    private final boolean dbUp;
    private final long heapUsedMb;
    private final long heapMaxMb;

    public HealthStatus(boolean dbUp, long heapUsedMb, long heapMaxMb) {
        this.dbUp = dbUp;
        this.heapUsedMb = heapUsedMb;
        this.heapMaxMb = heapMaxMb;
    }

    public boolean isDbUp() { return dbUp; }
    public long getHeapUsedMb() { return heapUsedMb; }
    public long getHeapMaxMb() { return heapMaxMb; }
}

