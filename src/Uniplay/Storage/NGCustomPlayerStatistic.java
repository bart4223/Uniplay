package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;

public abstract class NGCustomPlayerStatistic extends NGUniplayObject {

    public String FName;
    public NGPlayer FPlayer;

    public NGCustomPlayerStatistic(NGPlayer aPlayer, String aName) {
        super();
        FName = aName;
        FPlayer = aPlayer;
    }

    public String getName() {
        return FName;
    }

    public NGPlayer getPlayer() {
        return FPlayer;
    }

}
