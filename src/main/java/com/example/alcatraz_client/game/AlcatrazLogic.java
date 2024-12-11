package com.example.alcatraz_client.game;

import at.falb.games.alcatraz.api.Alcatraz;
import at.falb.games.alcatraz.api.IllegalMoveException;
import com.example.alcatraz_client.PortFetcher;
import com.example.alcatraz_client.data.Client;
import com.example.alcatraz_client.data.RestMove;
import com.example.alcatraz_client.rest.Caller;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlcatrazLogic {

    @Autowired
    private PortFetcher portFetcher;

    Alcatraz a = new Alcatraz();

    public AlcatrazLogic(PortFetcher portFetcher) {
        this.portFetcher = portFetcher;
    }

    public void init(int amountOfPlayers, List<Client> clients) throws JsonProcessingException {
        Client selfClient = clients.stream().filter(client -> client.getPort() == Integer.parseInt(portFetcher.getPort())).toList().get(0);
        a.init(amountOfPlayers, clients.indexOf(selfClient));//Mb set index in a list from Controller as an id to have consistency.
        for (int i = 0; i < amountOfPlayers; i++) {
            a.getPlayer(i).setName(clients.get(i).getUsername());
        }
        a.addMoveListener(new AlcatrazMoveListener(clients, a) {
        });
        a.showWindow();
        a.start();
    }

    public void makeMove(RestMove move) {
        try {
            int playerId = move.getPlayerId();
            a.doMove(a.getPlayer(playerId), a.getPrisoner(move.getPrisonerId()), move.getRowOrCol(), move.getRow(), move.getCol());
            System.out.println("Player" + a.getPlayer(move.getPlayerId()).getName() + "is" + "moving " + a.getPrisoner(move.getPrisonerId()));
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }
    }
}
