package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;

public class NGCustomPlayer extends NGUniplayObject {

    protected String FName;

    public NGCustomPlayer(String aName) {
        super();
        FName = aName;
    }

    public String getName() {
        return FName;
    }

}
