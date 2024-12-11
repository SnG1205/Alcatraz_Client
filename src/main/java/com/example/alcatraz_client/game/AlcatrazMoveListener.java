package com.example.alcatraz_client.game;

import at.falb.games.alcatraz.api.*;
import com.example.alcatraz_client.data.Client;
import com.example.alcatraz_client.data.RestMove;
import com.example.alcatraz_client.rest.Caller;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class AlcatrazMoveListener implements MoveListener {
    private List<Client> clients;
    private Alcatraz a;
    private Caller caller = new Caller();

    public AlcatrazMoveListener(List<Client> clients, Alcatraz a) {
        this.clients = clients;
        this.a = a;
    }

    @Override
    public void moveDone(Player player, Prisoner prisoner, int rowOrCol, int row, int col) {
        System.out.println("Player" + player.getName() + "is" + "moving " + prisoner + " to " + (rowOrCol == Alcatraz.ROW ? "row" : "col") + " " + (rowOrCol == Alcatraz.ROW ? row : col));
        List<Client> otherClients = clients.stream().filter(client -> !client.getUsername().equals(player.getName())).toList();
        RestMove move = new RestMove(player.getId(), prisoner.getId(), rowOrCol, row, col);
        otherClients.forEach(otherClient -> {
            try {
                caller.sendMove(move, otherClient.getPort());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void gameWon(Player player) {
        System.out.println("Player " + player.getId() + " wins.");
        //a.closeWindow();
    }
}
