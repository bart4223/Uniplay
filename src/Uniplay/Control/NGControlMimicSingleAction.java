package Uniplay.Control;

import Uniplay.Storage.NGCustomGame;

public abstract class NGControlMimicSingleAction extends NGCustomControlMimic {

    public NGControlMimicSingleAction(NGControlMimicManager aManager, NGCustomGame aGame, String aName, Kind aKind) {
        super(aManager, aGame, aName, aKind);
    }

}
