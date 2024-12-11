package com.example.alcatraz_client.rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PortStartupRunner implements ApplicationListener<ApplicationReadyEvent> {

    private final WebServerApplicationContext context;
    private int port;

    public PortStartupRunner(WebServerApplicationContext context) {
        this.context = context;
    }

    public int getPort() {
        return port;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        port = context.getWebServer().getPort();
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
