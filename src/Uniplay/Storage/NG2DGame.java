package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniplay.Kernel.NGGameEngineMemoryCellValueItem;
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
        writeLog(String.format("Current level is \"%s\"[%s].", FLevel.getCaption(), FLevel.getName()));
    }

    @Override
    protected void DoStart() {
        super.DoStart();
        resetPlayers();
        removeAllNPCs();
        NG2DLevel level = loadLevel(String.format(C_LEVEL_NAME, FLevelIndex));
        setLevel(level);
    }

    protected NG2DLevel loadLevel(String aName) {
        NG2DLevelManager lm = getLevelManager();
        NG2DLevel level = lm.addLevel(aName);
        NGObjectDeserializer Deserializer = new NGObjectXMLDeserializerFile(level, String.format("resources/levels/%s.ulf", aName));
        Deserializer.setLogManager(getLogManager());
        if (Deserializer.deserializeObject()) {
            reallocateLevelMemory(level);
            loadLevelToMemory(level);
            writeLog(String.format("Level \"%s\"[%s] loaded.", level.getCaption(), level.getName()));
        }
        return level;
    }

    protected void reallocateLevelMemory(NG2DLevel aLevel) {
        getMemoryManager().reallocateMemory(getMemoryName(), 1, (int)aLevel.getGameFieldSize().getWidth(), (int)aLevel.getGameFieldSize().getHeight());
    }

    protected void loadLevelToMemory(NG2DLevel aLevel) {
        Integer page = 1;
        NGGameEngineMemoryCellValueItem item;
        ArrayList<NGGameEngineMemoryCellValueItem> items = new ArrayList<NGGameEngineMemoryCellValueItem>();
        for (NG2DGameFieldLayer layer : aLevel.getGameField().getLayers()) {
            Integer base = 0;
            Integer offset = 0;
            for (NGGameEngineMemoryCell cell : layer.getCells()) {
                item = new NGGameEngineMemoryCellValueItem(new NGGameEngineMemoryAddress(page, base, offset), cell.getValue());
                items.add(item);
                offset = offset + 1;
                if (offset >= aLevel.getGameFieldSize().getWidth()) {
                    base = base + 1;
                    offset = 0;
                }
            }
            page = page + 1;
        }
        getMemoryManager().setCellsValue(getMemoryName(),items);
        writeLog(String.format("%d cell(s) value in memory [%s] stored.", items.size(), getMemoryName()));
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
