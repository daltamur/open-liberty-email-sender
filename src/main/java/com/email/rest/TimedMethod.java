package com.email.rest;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;

@Singleton
public class TimedMethod {

    @Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
    public void PrintHello() {
        System.out.println("Hello!");
    }
}
