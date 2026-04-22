package com.example.reddit.usecase;

import com.example.reddit.domain.UserRepository;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class HealthService {

    private final UserRepository userRepository;

    public HealthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public HealthStatus check() {

        boolean dbUp;

        try {
            userRepository.countUsers();
            dbUp = true;
        } catch (Exception e) {
            dbUp = false;
        }

        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heap = memBean.getHeapMemoryUsage();

        return new HealthStatus(
                dbUp,
                heap.getUsed() / (1024 * 1024),
                heap.getMax() / (1024 * 1024)
        );
    }
}

