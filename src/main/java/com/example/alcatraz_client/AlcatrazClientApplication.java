package com.example.alcatraz_client;

import com.example.alcatraz_client.game.gui.AlcatrazGui;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class AlcatrazClientApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        ApplicationContext applicationContext = SpringApplication.run(AlcatrazClientApplication.class, args);
        PortFetcher portFetcher = applicationContext.getBean(PortFetcher.class);
        AlcatrazGui.drawBoard(portFetcher);
    }

}
