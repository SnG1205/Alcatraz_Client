package com.example.alcatraz_client.game;

import at.falb.games.alcatraz.api.Alcatraz;
import at.falb.games.alcatraz.api.IllegalMoveException;
import at.falb.games.alcatraz.api.MoveListener;
import at.falb.games.alcatraz.api.Player;
import at.falb.games.alcatraz.api.Prisoner;
import com.example.alcatraz_client.PortFetcher;
import com.example.alcatraz_client.data.Client;
import com.example.alcatraz_client.data.RestMove;
import com.example.alcatraz_client.rest.Caller;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlcatrazLogic  {

    @Autowired
    private PortFetcher portFetcher; //Todo probably doesn`t work properly

    Alcatraz a = new Alcatraz();
    Caller c = new Caller(); //Todo mb delete later

    public AlcatrazLogic(PortFetcher portFetcher) {
        this.portFetcher = portFetcher;
    }

    public void init(int amountOfPlayers, List<Client> clients) throws JsonProcessingException {
        Client selfClient = clients.stream().filter(client -> client.getPort() == Integer.parseInt(portFetcher.getPort())).toList().get(0);
        //Client selfClient = clients.stream().filter(client -> client.getPort() == 8082).toList().get(0);
        a.init(amountOfPlayers, clients.indexOf(selfClient));//Mb set index in a list from Controller as an id to have consistency.
        for(int i = 0; i < amountOfPlayers; i++) {
            a.getPlayer(i).setName(clients.get(i).getUsername());
        }
        a.addMoveListener(new AlcatrazMoveListener(clients, a) {});
        a.showWindow();
        a.start();
    }

    public void makeMove(RestMove move) {
        try{
            a.doMove(a.getPlayer(move.getPlayerId()), a.getPrisoner(move.getPrisonerId()), move.getRowOrCol(), move.getRow(), move.getCol());
            System.out.println("Player" + a.getPlayer(move.getPlayerId()).getName() + "is" + "moving " + a.getPrisoner(move.getPrisonerId()));
        }
        catch (IllegalMoveException e) {
            e.printStackTrace();
        }
    }
}
