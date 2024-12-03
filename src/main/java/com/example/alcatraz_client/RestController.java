package com.example.alcatraz_client;

import com.example.alcatraz_client.data.Player;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private List<Player> players = new ArrayList<>();

    @PostMapping("/")
    public String setPlayers(@RequestBody List<Player> otherPlayers){
        players.addAll(otherPlayers);
        players.forEach(player -> System.out.println(player.getUsername()));
        return "Players added successfully";
    }

    public List<Player> getPlayers() {
        return players;
    }
}
