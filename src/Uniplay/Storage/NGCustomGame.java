package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;

public abstract class NGCustomGame extends NGUniplayObject {

    protected String FName;

    public NGCustomGame(String aName) {
        super();
        FName = aName;
    }

    public String getName() {
        return FName;
    }

}
