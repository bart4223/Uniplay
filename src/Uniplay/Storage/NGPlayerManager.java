package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

import java.util.ArrayList;

public class NGPlayerManager extends NGUniplayComponent {

    protected ArrayList<NGCustomPlayer> FPlayers;
    protected NGCustomPlayer FCurrentPlayer;

    protected NGCustomPlayer getPlayer(String aName) {
        for (NGCustomPlayer player : FPlayers) {
            if (player.getName().equals(aName)) {
                return player;
            }
        }
        return null;
    }

    public NGPlayerManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FPlayers = new ArrayList<NGCustomPlayer>();
    }

    public NGPlayer newPlayer(String aName, String aNickname) {
        NGPlayer player = new NGPlayer(aName, aNickname);
        writeLog(String.format("Player %s[%s] added.", player.getName(), player.getNickname()));
        return player;
    }

    public NGNonPlayer newNonPlayer(String aName) {
        NGNonPlayer player = new NGNonPlayer(aName);
        writeLog(String.format("NPC %s added.", player.getName()));
        return player;
    }

    public void setCurrentPlayer(String aName) {
        FCurrentPlayer = getPlayer(aName);
        if (FCurrentPlayer != null)
            writeLog(String.format("Current player is %s", FCurrentPlayer.getName()));
        else
            writeLog("No current player available");
    }

    public NGCustomPlayer getCurrentPlayer() {
        return FCurrentPlayer;
    }

}
