package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

public abstract class NGCustomGame extends NGUniplayComponent {

    protected void DoShowStages() {

    }

    public NGCustomGame(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
    }

    public void showStages() {
        DoShowStages();
    }

}
