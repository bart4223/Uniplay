package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

import java.util.ArrayList;

public class NGGameManager extends NGUniplayComponent {

    protected ArrayList<NGCustomGame> FGames;

    public NGGameManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FGames = new ArrayList<NGCustomGame>();
    }

    public void addGame(NGCustomGame aGame) {
        FGames.add(aGame);
    }

    public void removeGame(NGCustomGame aGame) {
        FGames.remove(aGame);
    }

}
