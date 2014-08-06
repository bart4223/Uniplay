package Uniplay.Storage;

import java.util.ArrayList;

public class NG2DGame extends NGCustomGame {

    protected ArrayList<NG2DGamePlayerItem> FPlayers;
    protected ArrayList<NG2DGamePlayerItem> FNPCs;
    protected NG2DLevel FLevel;

    protected NG2DLevel getLevel() {
        return FLevel;
    }

    @Override
    protected void DoStart() {
        super.DoStart();
        resetPlayers();
        removeAllNPCs();
        // ToDo LoadLevel
    }

    protected void resetPlayers() {
        for (NG2DGamePlayerItem item : FPlayers) {
            item.reset();
        }
    }

    protected void removeAllNPCs() {
        FNPCs.clear();
    }

    protected void addPlayer(NGPlayer aPlayer, Integer aX, Integer aY, Integer aMaxLives) {
        NG2DGamePlayerItem item = new NG2DGamePlayerItem(aPlayer, aMaxLives);
        item.setPosition(aX, aY);
        FPlayers.add(item);
    }

    protected void addNPC(NGNonPlayer aNPC, Integer aX, Integer aY, Integer aMaxLives) {
        NG2DGamePlayerItem item = new NG2DGamePlayerItem(aNPC, aMaxLives);
        item.setPosition(aX, aY);
        FNPCs.add(item);
    }

    public NG2DGame(NGGameManager aManager, String aName) {
        super(aManager, aName);
        FPlayers = new ArrayList<NG2DGamePlayerItem>();
        FNPCs = new ArrayList<NG2DGamePlayerItem>();
        FLevel = null;
    }

    public void removeAllPlayers() {
        FPlayers.clear();
    }

    public void addPlayer(NGPlayer aPlayer) {
        addPlayer(aPlayer, 0, 0, 0);
        writeLog(String.format("Player [%s] added in game [%s].", aPlayer.getName(), getName()));
    }

}
