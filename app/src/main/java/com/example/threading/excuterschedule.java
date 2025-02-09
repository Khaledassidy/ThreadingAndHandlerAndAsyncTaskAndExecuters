package com.example.threading;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

//Okay, I understand. Let's break down ScheduledFuture in a much simpler way, focusing on its core purpose and the "big picture" idea behind it.
//Imagine a Restaurant Order System
//Think of a busy restaurant where customers place orders, and the kitchen staff prepares the food.
//Customers (Your Code): You, as a developer, are like the customers. You want to order some tasks to be done (like preparing food).
//Kitchen Staff (ScheduledExecutorService) :  The kitchen staff is like the ScheduledExecutorService. They are responsible for taking orders and preparing the food.
//Orders (Tasks): The tasks you want to be done (like "cook a burger" or "bake a pizza") are like the orders.
//Order Tickets (ScheduledFuture): When you place an order, the kitchen gives you an order ticket. This ticket is like the ScheduledFuture.
//What is the Order Ticket (ScheduledFuture) For?
//The order ticket (ScheduledFuture) has a few important purposes:
//Proof of Order: It's proof that you placed an order. It's a way to keep track of your order.
//Estimated Time: The ticket might have an estimated time when your food will be ready (e.g., "15 minutes"). This is like the "scheduled" part of ScheduledFuture. You know that the task will be done sometime in the future.
//Checking Order Status: You can look at the ticket to see if your food is ready yet.
//isDone(): You can check if the food is ready (the task is complete).
//Getting Your Food: When the food is ready, you give the ticket to the kitchen, and they give you your food.
//get(): This is like giving the ticket to the kitchen and getting the result of your task (e.g., the cooked burger).
//Cancelling Your Order: If you change your mind, you can take the ticket back to the kitchen and cancel your order.
//cancel(): This is like cancelling the task. You tell the kitchen to stop preparing your food.
//Manage your orders: you can have many tickets and manage them.
//How Does This Relate to ScheduledFuture?
//You (Your Code): You want to schedule a task to run later (e.g., "send an email in 5 minutes" or "check for updates every 30 seconds").
//ScheduledExecutorService: You give the task to the ScheduledExecutorService (the kitchen).
//ScheduledFuture: The ScheduledExecutorService gives you back a ScheduledFuture (the order ticket).
//isDone(): You can use isDone() to check if the task is finished.
//get(): You can use get() to wait for the task to finish and get the result (if there is one).
//cancel(): You can use cancel() to stop the task before it's done.
//Manage your task: you can have many ScheduledFuture and manage them.
//The Big Idea
//The big idea behind ScheduledFuture is to:
//Schedule a task to run later.
//Give you a way to keep track of that task.
//Allow you to check if the task is done, get the result, or cancel it.
//Manage your task.
//Why is This Useful?
//Asynchronous Operations: It lets you do things in the background without blocking your main program.
//        Time-Based Tasks: It's perfect for tasks that need to happen at a specific time or after a delay.
//Repeating Tasks: It's great for tasks that need to happen repeatedly (like checking for updates).
//Control: It gives you control over your scheduled tasks.
//In Simple Terms
//ScheduledFuture is like a receipt for a task you've scheduled to run later. It lets you check on the task, get the result, or cancel it.
//I hope this restaurant analogy and the simplified explanation make the purpose of ScheduledFuture much clearer! Let me know if you have any more questions.
public class excuterschedule {





        private TextView textView;
        private ProgressBar progressBar;
        private ScheduledExecutorService executorService;
        private ScheduledFuture<?> scheduledFuture;
        private Handler mainThreadHandler;
        private int progress;

        public excuterschedule(TextView textView, ProgressBar progressBar) {
            this.textView = textView;
            this.progressBar = progressBar;
            this.executorService = Executors.newSingleThreadScheduledExecutor();
            this.mainThreadHandler = new Handler(Looper.getMainLooper());
            this.progress = 0;
        }

        public void startTask() {
            // Show theprogress bar and text view on the main thread
            mainThreadHandler.post(() -> {
                textView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
                textView.setText("0/100");
            });

            Runnable task = () -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }

                    progress += 10;
                    // Update the progress bar and text view on the main thread
                    mainThreadHandler.post(() -> {
                        progressBar.incrementProgressBy(10);
                        textView.setText(progressBar.getProgress() + "/100");
                    });
                }

                // Hide the progress bar and text view on the main thread
                mainThreadHandler.post(() -> {
                    textView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                });
            };

            scheduledFuture = (ScheduledFuture<?>) executorService.submit(task);
        }

        public void stopTask() {
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
            executorService.shutdown();
        }

}
