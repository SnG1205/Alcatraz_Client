package com.example.alcatraz_client;

import com.example.alcatraz_client.game.gui.AlcatrazGui;
import com.example.alcatraz_client.rest.PortStartupRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;


@SpringBootApplication
public class AlcatrazClientApplication {

    public static void main(String[] args){
        System.setProperty("java.awt.headless", "false");
        ApplicationContext applicationContext = SpringApplication.run(AlcatrazClientApplication.class, args);
        PortFetcher portFetcher = applicationContext.getBean(PortFetcher.class);
        //PortStartupRunner portStartupRunner = applicationContext.getBean(PortStartupRunner.class);
        System.out.println("Environment Variable SERVER_PORT: " + System.getenv("SERVER_PORT"));
        int port = Integer.parseInt(portFetcher.getPort());
        //System.out.println(port);
        //System.out.println(portStartupRunner.getPort());
        AlcatrazGui.drawBoard(port);
    }

}
