package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniplay.Kernel.NGGameEngineMemoryCellValueItem;
import Uniplay.NGGameEngineConstants;
import Uniwork.Base.NGObjectDeserializer;
import Uniwork.Base.NGObjectXMLDeserializerFile;
import Uniwork.Base.NGPropertyItem;
import Uniwork.Misc.NGStrings;

import java.util.concurrent.CopyOnWriteArrayList;

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
    protected void DoBeforeStart() {
        super.DoBeforeStart();
        FLevelIndex = 1;
    }

    @Override
    protected void DoBeforeStartLevel() {
        super.DoBeforeStartLevel();
        NG2DLevel level = null;
        BeforeLoadLevel();
        try {
            level = loadLevel(String.format(C_LEVEL_NAME, FLevelIndex));                    }
        finally {
            AfterLoadLevel(level);
            NG2DGameFieldLayer layer = getCurrentGameFieldLayer();
            if (layer != null) {
                loadLevelToMemory(level, layer);
            }
        }
    }

    @Override
    protected void DoNextLevel() {
        super.DoNextLevel();
        FLevelIndex++;
    }

    protected void BeforeLoadLevel() {

    }

    protected void assignGameObjects(NG2DLevel aLevel) {

    }

    protected void assignGameCharacters(NG2DLevel aLevel) {
        assignPlayerPositions(getCurrentGameFieldLayer());
        for (NGCustomGameCharacter pc : FPCs) {
            Object obj = aLevel.getProp("LIVES");
            if (obj instanceof Integer) {
                pc.setMaxLives((Integer)obj);
            }
        }
    }

    protected void AfterLoadLevel(NG2DLevel aLevel) {
        reallocateLevelMemory(aLevel);
        setCurrentLevel(aLevel);
        NG2DGameFieldLayer defaultlayer = aLevel.getGameField().getLayer("DEFAULT");
        if (defaultlayer != null) {
            setCurrentGameFieldLayer(defaultlayer);
        }
        assignGameCharacters(aLevel);
        assignGameObjects(aLevel);
    }

    protected NG2DLevel loadLevel(String aName) {
        NG2DLevelManager lm = getLevelManager();
        NG2DLevel level = lm.addLevel(aName);
        NGObjectDeserializer Deserializer = new NGObjectXMLDeserializerFile(level, String.format("resources/levels/%s.ulf", aName));
        Deserializer.setLogManager(getLogManager());
        if (Deserializer.deserializeObject()) {
            writeLog(String.format("Level \"%s\"[%s] loaded.", level.getCaption(), level.getName()));
        }
        return level;
    }

    protected void assignPlayerPositions(NG2DGameFieldLayer aLayer) {
        Integer i = 0;
        if (FPCs.size() > i) {
            for (NGPropertyItem prop : aLayer.getProps().getItems()) {
                String obj = NGStrings.getStringPos(prop.getName(), "\\.", 3);
                if (obj.equals("PLAYER")) {
                    String op = NGStrings.getStringPos(prop.getName(), "\\.", 4);
                    if (op.equals("POSITION")) {
                        NG2DObjectPosition pos = (NG2DObjectPosition)prop.getValue();
                        setPCPosition(i, pos.getX(), pos.getY());
                        i++;
                        if (i >= FPCs.size()) {
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
        CopyOnWriteArrayList<NGGameEngineMemoryCellValueItem> items = new CopyOnWriteArrayList<NGGameEngineMemoryCellValueItem>();
        Integer base = 0;
        Integer offset = 0;
        for (NGGameEngineMemoryCell cell : aLayer.getCells()) {
            NGGameEngineMemoryAddress address = new NGGameEngineMemoryAddress(1, base, offset);
            item = new NGGameEngineMemoryCellValueItem(address, createMemoryCellValueFrom(address, cell.getValue()));
            items.add(item);
            offset = offset + 1;
            if (offset >= aLevel.getGameFieldSize().getWidth()) {
                base = base + 1;
                offset = 0;
            }
        }
        getMemoryManager().setCellsValue(getMemoryName(), items);
        writeLog(String.format("%d cell(s) value in memory [%s] stored.", items.size(), getMemoryName()));
    }

    protected Class getGameCharacterClass() {
        return NG2DGameCharacter.class;
    }

    protected NG2DGameCharacter createGameCharacter(NGCustomPlayer aPlayer) {
        try {
            return (NG2DGameCharacter)(getGameCharacterClass().getConstructor(NGCustomGame.class, NGCustomPlayer.class).newInstance(this, aPlayer));
        }
        catch (Exception e) {
            writeError("createGameCharacter", e.getMessage());
        }
        return null;
    }

    protected void add2DGamePC(NGPlayer aPlayer, Integer aLayer, double aX, double aY, Integer aMaxLives) {
        NG2DGameCharacter character = createGameCharacter(aPlayer);
        character.setPosition(aX, aY);
        character.setMaxLives(aMaxLives);
        character.setLayer(aLayer);
        addPC(character);
    }

    protected void add2DGameNPC(NGNonPlayer aNPC, Integer aLayer, double aX, double aY, Integer aMaxLives) {
        NG2DGameCharacter character = createGameCharacter(aNPC);
        character.setPosition(aX, aY);
        character.setMaxLives(aMaxLives);
        character.setLayer(aLayer);
        addNPC(character);
    }

    protected NG2DLevelManager getLevelManager() {
        if (FLevelManager == null) {
            FLevelManager = (NG2DLevelManager)ResolveObject(NGGameEngineConstants.CMP_2DLEVEL_MANAGER, NG2DLevelManager.class);
        }
        return FLevelManager;
    }

    protected void raiseCharacterPositionChangedEvent(NG2DGameCharacter aPlayerItem) {
        NG2DGameCharacterPositionChanged event = new NG2DGameCharacterPositionChanged(this, aPlayerItem);
        raiseEvent(NGGameEngineConstants.EVT_GAME_CHARACTER_POSITION_CHANGED, event);
    }

    protected void raiseObjectPositionChangedEvent(NG2DGameObject aObjectItem) {
        NG2DGameObjectPositionChanged event = new NG2DGameObjectPositionChanged(this, aObjectItem);
        raiseEvent(NGGameEngineConstants.EVT_GAME_OBJECT_POSITION_CHANGED, event);
    }

    protected NG2DGameCharacter get2DGamePC(Integer aIndex) {
        return (NG2DGameCharacter)FPCs.get(aIndex);
    }

    public NG2DGame(NGGameManager aManager, String aName) {
        super(aManager, aName);
        FCurrentLevel = null;
        FCurrentGameFieldLayer = null;
        FLevelManager = null;
        FLevelIndex = 1;
    }

    public void add2DGamePC(NGPlayer aPlayer) {
        add2DGamePC(aPlayer, 1, 0.0, 0.0, 0);
    }

    public void setPCPosition(Integer aIndex, double aX, double aY) {
        setPCPosition(get2DGamePC(aIndex), aX, aY);
    }

    public void setPCPosition(NG2DGameCharacter aPlayerItem, double aX, double aY) {
        aPlayerItem.setPosition(aX, aY);
        raiseCharacterPositionChangedEvent(aPlayerItem);
        writeLog(String.format("Player's [%s] position is (%.1f/%.1f).", aPlayerItem.getPlayer().getName(), aPlayerItem.getPosition().getX(), aPlayerItem.getPosition().getY()));
    }

    public void setObjectPosition(NG2DGameObject aObjectItem, double aX, double aY) {
        aObjectItem.setPosition(aX, aY);
        raiseObjectPositionChangedEvent(aObjectItem);
        writeLog(String.format("Object's position is (%.1f/%.1f).", aObjectItem.getPosition().getX(), aObjectItem.getPosition().getY()));
    }

    public NG2DGameCharacter getPCfromAddress(NGGameEngineMemoryAddress aAddress) {
        for (NGCustomGameCharacter item : FPCs) {
            NG2DGameCharacter character = (NG2DGameCharacter)item;
            if (character.IsFromAddress(aAddress)) {
                return character;
            }
        }
        return null;
    }

    public NG2DGameCharacter getNPCfromAddress(NGGameEngineMemoryAddress aAddress) {
        for (NGCustomGameCharacter item : FNPCs) {
            NG2DGameCharacter character = (NG2DGameCharacter)item;
            if (character.IsFromAddress(aAddress)) {
                return character;
            }
        }
        return null;
    }

}
