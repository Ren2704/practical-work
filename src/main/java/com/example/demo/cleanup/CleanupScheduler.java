package com.example.demo.cleanup;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CleanupScheduler {

    private final CleanupService cleanupService;

    @Scheduled(cron = "0 0 2 * * *")
    public void runCleanup() {
        cleanupService.clearingDeletedRecords();
    }
}
