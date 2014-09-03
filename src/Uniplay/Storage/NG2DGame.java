package Uniplay.Storage;

import Uniplay.Control.NGCustomControlMimic;
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
    protected void resetAll() {
        super.resetAll();
        resetPlayers();
        removeAllNPCs();
        FLevelIndex = 1;
    }

    @Override
    protected void DoBeforeStartLevel() {
        super.DoBeforeStartLevel();
        NG2DLevel level = loadLevel(String.format(C_LEVEL_NAME, FLevelIndex));
        setCurrentLevel(level);
        setCurrentGameFieldLayer(level.getGameField().getLayer("DEFAULT"));
        loadLevelToMemory(level, getCurrentGameFieldLayer());
        assignPlayerPositions(getCurrentGameFieldLayer());
    }

    @Override
    protected void DoNextLevel() {
        FLevelIndex++;
        ResetAllMimicActions();
        DoStartLevel();
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
                        NG2DGameObjectPosition pos = (NG2DGameObjectPosition)prop.getValue();
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
        getMemoryManager().reallocateMemory(getMemoryName(), 1, (int)aLevel.getGameFieldSize().getHeight(), (int)aLevel.getGameFieldSize().getWidth());
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

    protected void add2DGamePlayerItem(NGPlayer aPlayer, Integer aLayer, double aX, double aY, Integer aMaxLives) {
        NG2DGamePlayerItem item = new NG2DGamePlayerItem(this, aPlayer, aLayer, aMaxLives);
        item.setPosition(aX, aY);
        addPlayerItem(item);
    }

    protected void add2DGameNPCItem(NGNonPlayer aNPC, Integer aLayer, double aX, double aY, Integer aMaxLives) {
        NG2DGamePlayerItem item = new NG2DGamePlayerItem(this, aNPC, aLayer, aMaxLives);
        item.setPosition(aX, aY);
        addNPCItem(item);
    }

    protected NG2DLevelManager getLevelManager() {
        if (FLevelManager == null) {
            FLevelManager = (NG2DLevelManager)ResolveObject(NGGameEngineConstants.CMP_2DLEVEL_MANAGER, NG2DLevelManager.class);
        }
        return FLevelManager;
    }

    protected void raisePositionChangedEvent(NG2DGamePlayerItem aPlayerItem) {
        NG2DGamePlayerPositionChanged event = new NG2DGamePlayerPositionChanged(this, aPlayerItem);
        raiseEvent(NGGameEngineConstants.EVT_2DGAME_PLAYER_POSITION_CHANGED, event);
    }


    protected NG2DGamePlayerItem get2DGamePlayerItem(Integer aIndex) {
        return (NG2DGamePlayerItem)FPlayers.get(aIndex);
    }

    public NG2DGame(NGGameManager aManager, String aName) {
        super(aManager, aName);
        FCurrentLevel = null;
        FCurrentGameFieldLayer = null;
        FLevelManager = null;
        FLevelIndex = 1;
    }

    public void add2DGamePlayer(NGPlayer aPlayer) {
        add2DGamePlayerItem(aPlayer, 1, 0.0, 0.0, 0);
    }

    public void setPlayerPosition(Integer aIndex, double aX, double aY) {
        setPlayerPosition(get2DGamePlayerItem(aIndex), aX, aY);
    }

    public void setPlayerPosition(NG2DGamePlayerItem aPlayerItem, double aX, double aY) {
        aPlayerItem.setPosition(aX, aY);
        raisePositionChangedEvent(aPlayerItem);
        writeLog(String.format("Player's [%s] position is (%.1f/%.1f).", aPlayerItem.getPlayer().getName(), aPlayerItem.getPosition().getX(), aPlayerItem.getPosition().getY()));
    }

}
