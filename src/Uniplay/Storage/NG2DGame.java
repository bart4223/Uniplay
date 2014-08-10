package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniplay.Kernel.NGGameEngineMemoryCellValueItem;
import Uniplay.NGGameEngineConstants;
import Uniwork.Base.NGObjectDeserializer;
import Uniwork.Base.NGObjectXMLDeserializerFile;
import Uniwork.Base.NGPropertyItem;
import Uniwork.Misc.NGStrings;

import java.util.ArrayList;

public class NG2DGame extends NGCustomGame {

    private static final String C_LEVEL_NAME = "level_%d";

    protected ArrayList<NG2DGamePlayerItem> FPlayers;
    protected ArrayList<NG2DGamePlayerItem> FNPCs;
    protected NG2DLevel FCurrentLevel;
    protected NG2DGameFieldLayer FCurrentGameFieldLayer;
    protected NG2DLevelManager FLevelManager;
    protected Integer FLevelIndex;

    protected NG2DLevel getCurrentLevel() {
        return FCurrentLevel;
    }

    protected NG2DGameFieldLayer getCurrentGameFieldLayer() {
        return FCurrentGameFieldLayer;
    }

    protected void setCurrentLevel(NG2DLevel aLevel) {
        FCurrentLevel = aLevel;
        writeLog(String.format("Current level is \"%s\"[%s].", FCurrentLevel.getCaption(), FCurrentLevel.getName()));
    }

    protected void setCurrentGameFieldLayer(NG2DGameFieldLayer aLayer) {
        FCurrentGameFieldLayer = aLayer;
        writeLog(String.format("Current game field layer is [%s].", FCurrentGameFieldLayer.getName()));
    }

    @Override
    protected void DoStart() {
        super.DoStart();
        resetPlayers();
        removeAllNPCs();
        NG2DLevel level = loadLevel(String.format(C_LEVEL_NAME, FLevelIndex));
        setCurrentLevel(level);
        setCurrentGameFieldLayer(level.getGameField().getLayer("DEFAULT"));
        loadLevelToMemory(level, getCurrentGameFieldLayer());
        assignPlayerPositions(getCurrentGameFieldLayer());
    }

    protected NG2DLevel loadLevel(String aName) {
        NG2DLevelManager lm = getLevelManager();
        NG2DLevel level = lm.addLevel(aName);
        NGObjectDeserializer Deserializer = new NGObjectXMLDeserializerFile(level, String.format("resources/levels/%s.ulf", aName));
        Deserializer.setLogManager(getLogManager());
        if (Deserializer.deserializeObject()) {
            reallocateLevelMemory(level);
            writeLog(String.format("Level \"%s\"[%s] loaded.", level.getCaption(), level.getName()));
        }
        return level;
    }

    protected void assignPlayerPositions(NG2DGameFieldLayer aLayer) {
        Integer i = 0;
        if (FPlayers.size() > i) {
            for (NGPropertyItem prop : aLayer.getProps().getItems()) {
                String obj = NGStrings.getStringPos(prop.getName(), "\\.", 3);
                if (obj.equals("PLAYER")) {
                    String op = NGStrings.getStringPos(prop.getName(), "\\.", 4);
                    if (op.equals("POSITION")) {
                        NG2DGamePlayerPosition pos = (NG2DGamePlayerPosition)prop.getValue();
                        setPlayerPosition(i, pos.getX(), pos.getY());
                        i++;
                        if (i >= FPlayers.size()) {
                            return;
                        }
                    }
                }
            }

        }
    }

    protected void reallocateLevelMemory(NG2DLevel aLevel) {
        getMemoryManager().reallocateMemory(getMemoryName(), 1, (int)aLevel.getGameFieldSize().getWidth(), (int)aLevel.getGameFieldSize().getHeight());
    }

    protected void loadLevelToMemory(NG2DLevel aLevel, NG2DGameFieldLayer aLayer) {
        NGGameEngineMemoryCellValueItem item;
        ArrayList<NGGameEngineMemoryCellValueItem> items = new ArrayList<NGGameEngineMemoryCellValueItem>();
        Integer base = 0;
        Integer offset = 0;
        for (NGGameEngineMemoryCell cell : aLayer.getCells()) {
            item = new NGGameEngineMemoryCellValueItem(new NGGameEngineMemoryAddress(1, base, offset), cell.getValue());
            items.add(item);
            offset = offset + 1;
            if (offset >= aLevel.getGameFieldSize().getWidth()) {
                base = base + 1;
                offset = 0;
            }
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

    protected void addPlayer(NGPlayer aPlayer, Double aX, Double aY, Integer aMaxLives) {
        NG2DGamePlayerItem item = new NG2DGamePlayerItem(aPlayer, aMaxLives);
        item.setPosition(aX, aY);
        FPlayers.add(item);
    }

    protected void addNPC(NGNonPlayer aNPC, Double aX, Double aY, Integer aMaxLives) {
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

    protected void setPlayerPosition(Integer aIndex, double aX, double aY) {
        setPlayerPosition(FPlayers.get(aIndex), aX, aY);
    }

    protected void setPlayerPosition(NG2DGamePlayerItem aPlayerItem, double aX, double aY) {
        aPlayerItem.setPosition(aX, aY);
        writeLog(String.format("Player [%s] position is (%.1f/%.1f).", aPlayerItem.getPlayer().getName(), aPlayerItem.getPosition().getX(), aPlayerItem.getPosition().getY()));
    }

    public NG2DGame(NGGameManager aManager, String aName) {
        super(aManager, aName);
        FPlayers = new ArrayList<NG2DGamePlayerItem>();
        FNPCs = new ArrayList<NG2DGamePlayerItem>();
        FCurrentLevel = null;
        FCurrentGameFieldLayer = null;
        FLevelManager = null;
        FLevelIndex = 1;
    }

    public void removeAllPlayers() {
        FPlayers.clear();
    }

    public void addPlayer(NGPlayer aPlayer) {
        addPlayer(aPlayer, 0.0, 0.0, 0);
        writeLog(String.format("Player [%s] added in game [%s].", aPlayer.getName(), getName()));
    }

}
