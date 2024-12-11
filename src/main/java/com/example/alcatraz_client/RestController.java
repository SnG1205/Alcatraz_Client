package com.example.alcatraz_client;

import com.example.alcatraz_client.data.Client;
import com.example.alcatraz_client.data.RestMove;
import com.example.alcatraz_client.game.AlcatrazLogic;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private AlcatrazLogic alcatrazLogic;

    @PostMapping("/")
    public String setClients(@RequestBody List<Client> otherClients) throws JsonProcessingException {
        alcatrazLogic.init(otherClients.size(), otherClients);
        return "Players added successfully";
    }

    @PostMapping("/move")
    public String moveClients(@RequestBody RestMove move){
        alcatrazLogic.makeMove(move);
        return "Moved successfully";
    }
}
