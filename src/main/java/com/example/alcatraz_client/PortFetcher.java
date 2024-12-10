package com.example.alcatraz_client;

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
}
