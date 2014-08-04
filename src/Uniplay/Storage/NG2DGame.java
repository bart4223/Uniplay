package Uniplay.Storage;

import java.util.ArrayList;

public class NG2DGame extends NGCustomGame {

    protected ArrayList<NG2DGamePlayerItem> FPlayers;
    protected ArrayList<NG2DGamePlayerItem> FNPCs;
    protected NG2DLevel FLevel;

    public NG2DGame(String aName) {
        super(aName);
        FPlayers = new ArrayList<NG2DGamePlayerItem>();
        FNPCs = new ArrayList<NG2DGamePlayerItem>();
        FLevel = null;
    }

    public NG2DLevel getLevel() {
        return FLevel;
    }

}
