package Uniplay.Control;

import Uniplay.Storage.NGCustomGame;

public class NGControlMimicGameObjectExecution extends NGControlMimicPeriodicAction {

    @Override
    protected void DoHandleTick() {
        super.DoHandleTick();
        FGame.ExecuteGameObject(GameObjectName);
    }

    public NGControlMimicGameObjectExecution(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName);
        GameObjectName = "";
    }

    public String GameObjectName;

}
