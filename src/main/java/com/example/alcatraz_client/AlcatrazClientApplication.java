package com.example.alcatraz_client;

import com.example.alcatraz_client.data.Client;
import com.example.alcatraz_client.game.AlcatrazLogic;
import com.example.alcatraz_client.rest.Caller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AlcatrazClientApplication {

    public static void main(String[] args) {
        /*AlcatrazLogic alcatrazLogic = new AlcatrazLogic();
        List<Client> clients = List.of(new Client("s1", 8080), new Client("s2", 2), new Client("s3", 3) );
        alcatrazLogic.init(3, clients);*/
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(AlcatrazClientApplication.class, args);
    }

}
