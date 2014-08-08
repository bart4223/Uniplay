package Uniplay.Storage;

import Uniplay.NGGameEngineConstants;
import Uniwork.Base.NGObjectDeserializer;
import Uniwork.Base.NGObjectXMLDeserializerFile;

import java.util.ArrayList;

public class NG2DGame extends NGCustomGame {

    private static final String C_LEVEL_NAME = "level_%d";

    protected ArrayList<NG2DGamePlayerItem> FPlayers;
    protected ArrayList<NG2DGamePlayerItem> FNPCs;
    protected NG2DLevel FLevel;
    protected NG2DLevelManager FLevelManager;
    protected Integer FLevelIndex;

    protected NG2DLevel getLevel() {
        return FLevel;
    }

    protected void setLevel(NG2DLevel aLevel) {
        FLevel = aLevel;
    }

    @Override
    protected void DoStart() {
        super.DoStart();
        resetPlayers();
        removeAllNPCs();
        loadLevel(String.format(C_LEVEL_NAME, FLevelIndex));
    }

    protected void loadLevel(String aName) {
        NG2DLevelManager lm = getLevelManager();
        setLevel(lm.addLevel(aName));
        NGObjectDeserializer Deserializer = new NGObjectXMLDeserializerFile(getLevel(), String.format("resources/levels/%s.ulf", aName));
        Deserializer.setLogManager(getLogManager());
        if (Deserializer.deserializeObject()) {
            reallocateMemory();
            writeLog(String.format("Level \"%s\"[%s] loaded.", getLevel().getCaption(), getLevel().getName()));
        }
    }

    protected void reallocateMemory() {
        getMemoryManager().reallocateMemory(getMemoryName(), 1, (int)FLevel.getGameFieldSize().getWidth(), (int)FLevel.getGameFieldSize().getHeight());
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

    protected NG2DLevelManager getLevelManager() {
        if (FLevelManager == null) {
            FLevelManager = (NG2DLevelManager)ResolveObject(NGGameEngineConstants.CMP_2DLEVEL_MANAGER, NG2DLevelManager.class);
        }
        return FLevelManager;
    }

    public NG2DGame(NGGameManager aManager, String aName) {
        super(aManager, aName);
        FPlayers = new ArrayList<NG2DGamePlayerItem>();
        FNPCs = new ArrayList<NG2DGamePlayerItem>();
        FLevel = null;
        FLevelManager = null;
        FLevelIndex = 1;
    }

    public void removeAllPlayers() {
        FPlayers.clear();
    }

    public void addPlayer(NGPlayer aPlayer) {
        addPlayer(aPlayer, 0, 0, 0);
        writeLog(String.format("Player [%s] added in game [%s].", aPlayer.getName(), getName()));
    }

}
