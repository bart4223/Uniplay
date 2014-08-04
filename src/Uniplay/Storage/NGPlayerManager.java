package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

import java.util.ArrayList;

public class NGPlayerManager extends NGUniplayComponent {

    protected ArrayList<NGPlayer> FPlayers;
    protected NGPlayer FCurrentPlayer;

    protected NGPlayer getPlayer(String aName) {
        for (NGPlayer player : FPlayers) {
            if (player.getName().equals(aName)) {
                return player;
            }
        }
        return null;
    }

    protected NGPlayer setCurrentPlayer(NGPlayer aPlayer) {
        FCurrentPlayer = aPlayer;
        if (FCurrentPlayer != null)
            writeLog(String.format("Current player is %s", FCurrentPlayer.getName()));
        else
            writeLog("No current player available");
        return FCurrentPlayer;
    }

    public NGPlayerManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FPlayers = new ArrayList<NGPlayer>();
    }

    public NGPlayer newPlayer(String aName, String aNickname) {
        NGPlayer player = new NGPlayer(aName, aNickname);
        FPlayers.add(player);
        writeLog(String.format("Player %s[%s] added.", player.getName(), player.getNickname()));
        return player;
    }

    public NGPlayer setCurrentPlayer(String aName) {
        return setCurrentPlayer(getPlayer(aName));
    }

    public NGCustomPlayer getCurrentPlayer() {
        return FCurrentPlayer;
    }

    public ArrayList<NGPlayer> getPlayers() {
        return FPlayers;
    }

}
