package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

import java.util.ArrayList;

public class NGPlayerManager extends NGUniplayComponent {

    protected ArrayList<NGCustomPlayer> FPlayers;

    public NGPlayerManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FPlayers = new ArrayList<NGCustomPlayer>();
    }

    public NGPlayer newPlayer(String aName, String aNickname) {
        NGPlayer player = new NGPlayer(aName, aNickname);
        return player;
    }

    public NGNonPlayer newNonPlayer(String aName) {
        NGNonPlayer player = new NGNonPlayer(aName);
        return player;
    }

}
