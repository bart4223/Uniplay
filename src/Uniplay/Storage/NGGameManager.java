package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

import java.util.ArrayList;

public class NGGameManager extends NGUniplayComponent {

    protected ArrayList<NGCustomGame> FGames;

    protected void addGame(NGCustomGame aGame) {
        aGame.setLogManager(getLogManager());
        bindEventListernerTo(aGame);
        aGame.Initialize();
        FGames.add(aGame);
    }

    public NGGameManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FGames = new ArrayList<NGCustomGame>();
    }

    public NGCustomGame addGame(String aName, Class aClass) {
        NGCustomGame game = null;
        try {
            game = (NGCustomGame)aClass.getConstructor(NGGameManager.class, String.class).newInstance(this, aName);
            addGame(game);
            writeLog(String.format("Game [%s] added.", game.getName()));
        } catch (Exception e) {
            writeError("addGame", e.getMessage());
        }
        return game;
    }

    public void showGames() {
        for (NGCustomGame game : FGames) {
            game.showStages();
        }
    }

    public NGCustomGame getGame(String aName) {
        for (NGCustomGame game : FGames) {
            if (game.getName().equals(aName)) {
                return game;
            }
        }
        return null;
    }

}
