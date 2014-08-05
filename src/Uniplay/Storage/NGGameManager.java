package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

public class NGGameManager extends NGUniplayComponent {

    public NGGameManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
    }

    public void addGame(NGCustomGame aGame) {
        addSubComponent(aGame);
        writeLog(String.format("Game [%s] added.",aGame.getName()));
    }

    public void showGames() {
        for (NGUniplayComponent component : FSubComponents) {
            if (component instanceof NGCustomGame) {
                NGCustomGame game = (NGCustomGame)component;
                game.showStages();
            }
        }
    }

}
