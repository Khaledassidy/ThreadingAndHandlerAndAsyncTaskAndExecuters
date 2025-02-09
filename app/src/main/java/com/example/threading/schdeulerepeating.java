package com.example.threading;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class schdeulerepeating {

    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    ScheduledFuture<?> scheduledFuture;

    public void start() {
        Runnable task = () -> {
            System.out.println("hi how are you");
        };

        scheduledFuture = (ScheduledFuture<?>) scheduledExecutorService.scheduleWithFixedDelay(task,4,10, TimeUnit.SECONDS);


    }

    public void stopTask() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        scheduledExecutorService.shutdown();
    }

}

