package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;

public class NGPlayerItem extends NGUniplayObject {

    protected NGPlayer FPlayer;

    public NGPlayerItem(NGPlayer aPlayer) {
        super();
        FPlayer = aPlayer;
    }

    public NGPlayer getPlayer() {
        return FPlayer;
    }

}
