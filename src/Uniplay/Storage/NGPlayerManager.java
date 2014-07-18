package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;

import java.util.ArrayList;

public class NGPlayerManager extends NGUniplayObject {

    protected ArrayList<NGPlayer> FPlayers;

    public NGPlayerManager() {
        super();
        FPlayers = new ArrayList<NGPlayer>();
    }

    public NGPlayer newPlayer(String aName, String aNickname) {
        NGPlayer player = new NGPlayer(aName);
        player.setNickname(aNickname);
        return player;
    }

}
