package com.example.alcatraz_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PortFetcher {

    private Environment environment;

    public PortFetcher(Environment environment) {
        this.environment = environment;
    }

    public String getPort() {
        return environment.getProperty("local.server.port");
    }

    public void some(){
        System.out.println("Checking stuff");
    }
}
