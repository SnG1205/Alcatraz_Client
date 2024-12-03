package com.example.alcatraz_client.game;

import at.falb.games.alcatraz.api.Alcatraz;
import at.falb.games.alcatraz.api.IllegalMoveException;
import at.falb.games.alcatraz.api.MoveListener;
import at.falb.games.alcatraz.api.Player;
import at.falb.games.alcatraz.api.Prisoner;

public class AlcatrazLogic  {
    Alcatraz a = new Alcatraz();

    public void init(int amountOfPlayers, int id){
        a.init(amountOfPlayers, id); //Mb set index in a list from Controller as an id to have consistency.
    }
}
