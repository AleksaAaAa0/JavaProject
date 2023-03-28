package ru.tinkoff.edu.controller;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
@EnableScheduling
@EnableAsync
public class LinkUpdaterScheduler {


    //@Scheduled(fixedDelayString = "${app.scheduler.interval}")
    @Async
    @Scheduled(fixedDelay = 1000)
    public static void update(){
        System.out.println("QWDFQ");
    }
}
