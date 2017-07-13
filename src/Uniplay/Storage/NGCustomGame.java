package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObjectRegistration;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGCustomControlMimic;
import Uniplay.Kernel.*;
import Uniplay.Misc.NGTaskManager;
import Uniplay.NGGameEngine;
import Uniplay.NGGameEngineConstants;
import Uniplay.Sound.NGSoundManager;
import Uniwork.Misc.NGTickEvent;
import Uniwork.Misc.NGTickListener;
import javafx.application.Platform;

import java.util.concurrent.CopyOnWriteArrayList;

public abstract class NGCustomGame extends NGUniplayComponent implements NGTickListener, NGUniplayObjectRegistration {

    public enum State {Created, Initialized, Started, Hold, Finished}

    protected CopyOnWriteArrayList<NGCustomGameCharacter> FPCs;
    protected CopyOnWriteArrayList<NGCustomGameCharacter> FNPCs;
    protected CopyOnWriteArrayList<NGCustomGameObject> FGameObjects;
    protected NGGameManager FManager;
    protected NGPlayerManager FPlayerManager;
    protected NGGameEngineMemoryManager FMemoryManager;
    protected NGControlMimicManager FMimicManager;
    protected State FState;
    protected Integer FUpdateCount;

    protected void registerObjects() {

    }

    protected void DoShowStages() {

    }

    @Override
    protected Object DoResolveObject(String aName, Class aClass) {
        Object result = super.DoResolveObject(aName, aClass);
        if (result == null && FManager != null) {
            result = FManager.ResolveObject(aName, aClass);
        }
        return result;
    }

    protected void resetAll() {
        resetPCs();
        resetNPCs();
        resetGameObjects();
        ResetAllMimicActions();
    }

    protected void DoBeforeStart() {
        resetAll();
    }

    protected void DoStart() {
        raiseGameStartEvent();
    }

    protected void DoAfterStart() {
        collectPlayerStatistic();
        DoStartLevel();
    }

    protected void DoBeforeStartLevel() {
        removeAllNPCs();
    }

    protected void DoStartLevel() {
        DoBeforeStartLevel();
        try
        {
            InternalStartLevel();
        }
        finally {
            DoAfterStartLevel();
        }
    }

    protected void InternalStartLevel() {
        raiseLevelStartEvent();
    }

    protected void DoAfterStartLevel() {
        ActivateMimicActions(NGCustomControlMimic.Kind.permanent);
    }

    protected void DoFinishLevel() {
        raiseLevelFinishEvent();
        DeactivateAllMimicActions();
    }

    protected void DoBreak() {
        raiseGameBreakEvent();
    }

    protected void DoContinue() {
        raiseGameContinueEvent();
    }

    protected void DoFinish() {
        raiseGameFinishEvent();
        DeactivateAllMimicActions();
    }

    protected void collectPlayerStatistic() {
        for (NGCustomGameCharacter pc : FPCs) {
            NGPlayerGameStatistic statistic = getPlayerGameStatistic((NGPlayer)pc.getPlayer());
            statistic.incTotal();
            writeLog(statistic.toString());
        }
    }

    protected void DoNextLevel() {
        resetAll();
    }

    protected NGPlayerGameStatistic addPlayerGameStatistic(NGPlayer aPlayer) {
        return (NGPlayerGameStatistic)FPlayerManager.addStatistic(new NGPlayerGameStatistic(aPlayer, getName())).getStatistic();
    }

    protected NGPlayerGameStatistic getPlayerGameStatistic(NGPlayer aPlayer) {
        NGPlayerGameStatistic statistic = (NGPlayerGameStatistic)FPlayerManager.getStatistic(aPlayer, getName());
        if (statistic == null) {
            statistic = addPlayerGameStatistic(aPlayer);
        }
        return statistic;
    }

    protected void resetPCs() {
        for (NGCustomGameCharacter pc : FPCs) {
            pc.Reset();
        }
    }

    protected void resetNPCs() {
        for (NGCustomGameCharacter pc : FNPCs) {
            pc.Reset();
        }
    }

    protected void resetGameObjects() {
        for (NGCustomGameObject gameObject : FGameObjects) {
            gameObject.Reset();
        }
    }

    protected void addPC(NGCustomGameCharacter aPlayer) {
        FPCs.add(aPlayer);
        writeLog(String.format("Player character [%s] added in game [%s].", aPlayer.getPlayer().getName(), getName()));
    }

    protected void addNPC(NGCustomGameCharacter aNPC) {
        FNPCs.add(aNPC);
        writeLog(String.format("NPC [%s] added in game [%s].", aNPC.getPlayer().getName(), getName()));
    }

    protected void addGameObject(NGCustomGameObject aGameObject) {
        FGameObjects.add(aGameObject);
        writeLog(String.format("GameObject [%s] added in game [%s].", aGameObject.getName(), getName()));
    }

    protected NGSoundManager getSoundManager() {
        return (NGSoundManager)ResolveObject(NGGameEngineConstants.CMP_SOUND_MANAGER, NGSoundManager.class);
    }

    protected NGTaskManager getTaskManager() {
        return (NGTaskManager)ResolveObject(NGGameEngineConstants.CMP_TASK_MANAGER, NGTaskManager.class);
    }

    protected void raiseGameStartEvent() {
        raiseEvent(NGGameEngineConstants.EVT_GAME_START, new NGGameEventStartGame(this));
    }

    protected void raiseGameBreakEvent() {
        raiseEvent(NGGameEngineConstants.EVT_GAME_BREAK, new NGGameEventBreakGame(this));
    }

    protected void raiseGameContinueEvent() {
        raiseEvent(NGGameEngineConstants.EVT_GAME_CONTINUE, new NGGameEventContinueGame(this));
    }

    protected void raiseGameFinishEvent() {
        raiseEvent(NGGameEngineConstants.EVT_GAME_FINISH, new NGGameEventFinishGame(this));
    }

    protected void raiseLevelStartEvent() {
        raiseEvent(NGGameEngineConstants.EVT_GAME_LEVEL_START, new NGGameEventStartLevel(this));
    }

    protected void raiseLevelFinishEvent() {
        raiseEvent(NGGameEngineConstants.EVT_GAME_LEVEL_FINISH, new NGGameEventFinishLevel(this));
    }

    protected void assignMemoryCellValueFrom(NGGameEngineMemoryAddress aAddress, NGGameEngineMemoryObjectCellValue aCellValue, Object aObject) {

    }

    protected NGGameEngineMemoryObjectCellValue createMemoryCellValueFrom(NGGameEngineMemoryAddress aAddress, Object aObject) {
        NGGameEngineMemoryObjectCellValue result;
        try {
            result = (NGGameEngineMemoryObjectCellValue)getMemoryCellValueClass().getConstructor().newInstance();
        }
        catch (Exception e) {
            result = null;
            writeError("createMemoryCellValue", e.getMessage());
        }
        assignMemoryCellValueFrom(aAddress, result, aObject);
        return result;
    }

    protected Class getMemoryCellValueClass() {
        return NGGameEngineMemoryObjectCellValue.class;
    }

    @Override
    protected void DoInitialized() {
        super.DoInitialized();
        FState = State.Initialized;
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        registerObjects();
    }

    protected void DoHandleTick(NGTickEvent aEvent) {

    }

    protected void raiseExecuteGameObject(NGCustomGameObject aGameObject) {
        NGCustomGameObjectExecuted event = new NGCustomGameObjectExecuted(this, aGameObject);
        raiseEvent(NGGameEngineConstants.EVT_GAME_OBJECT_EXECUTED, event);
    }

    protected void DoExecuteGameObject(NGCustomGameObject aGameObject) {
        aGameObject.Execute();
        raiseExecuteGameObject(aGameObject);
    }

    public NGCustomGame(NGGameManager aManager, String aName) {
        super(aManager, aName);
        FPCs = new CopyOnWriteArrayList<NGCustomGameCharacter>();
        FNPCs = new CopyOnWriteArrayList<NGCustomGameCharacter>();
        FGameObjects = new CopyOnWriteArrayList<NGCustomGameObject>();
        FManager = aManager;
        FPlayerManager = null;
        FState = State.Created;
        getMemoryManager().addMemory(getMemoryName(), 0, 0, 0, getMemoryCellValueClass());
        FUpdateCount = 0;
    }

    public NGGameManager getManager() {
        return FManager;
    }

    public NGPlayerManager getPlayerManager() {
        if (FPlayerManager == null) {
            FPlayerManager = (NGPlayerManager)ResolveObject(NGGameEngineConstants.CMP_PLAYER_MANAGER, NGPlayerManager.class);
        }
        return FPlayerManager;
    }

    public CopyOnWriteArrayList<NGCustomGameCharacter> getPCs() {
        return FPCs;
    }

    public CopyOnWriteArrayList<NGCustomGameCharacter> getNPCs() {
        return FNPCs;
    }

    public CopyOnWriteArrayList<NGCustomGameObject> getGameObjects() {
        return FGameObjects;
    }

    public NGCustomGameObject getGameObject(String aName) {
        for (NGCustomGameObject obj : FGameObjects) {
            if (obj.getName().equals(aName)) {
                return obj;
            }
        }
        return null;
    }

    public void removeAllPCs() {
        FPCs.clear();
    }

    public void removeAllNPCs() {
        FNPCs.clear();
    }

    public void removeAllGameObjects() {
        FGameObjects.clear();
    }

    public NGGameEngineMemoryManager getMemoryManager() {
        if (FMemoryManager == null) {
            FMemoryManager = (NGGameEngineMemoryManager)ResolveObject(NGGameEngineConstants.CMP_MEMORY_MANAGER, NGGameEngineMemoryManager.class);
        }
        return FMemoryManager;
    }

    public NGControlMimicManager getMimicManager() {
        if (FMimicManager == null) {
            FMimicManager = (NGControlMimicManager)ResolveObject(NGGameEngineConstants.CMP_CONTROLMIMIC_MANAGER, NGControlMimicManager.class);
        }
        return FMimicManager;
    }

    public void showStages() {
        DoShowStages();
    }

    public void Start() {
        if (FState == State.Initialized || FState == State.Finished) {
            writeLog(String.format("Game [%s] starting...", getName()));
            DoBeforeStart();
            try
            {
                DoStart();
            }
            finally {
                DoAfterStart();
            }
            FState = State.Started;
            writeLog(String.format("Game [%s] started.", getName()));
        }
    }

    public void Break() {
        if (FState == State.Started) {
            DoBreak();
            FState = State.Hold;
            writeLog(String.format("Game [%s] hold.", getName()));
        }
    }

    public void Continue() {
        if (FState == State.Hold) {
            DoContinue();
            FState = State.Started;
            writeLog(String.format("Game [%s] started.", getName()));
        }
    }

    public void Finish() {
        if (FState == State.Started || FState == State.Hold) {
            DoFinish();
            FState = State.Finished;
            writeLog(String.format("Game [%s] finished.", getName()));
        }
    }

    public void Restart() {
        Finish();
        Start();
    }

    public State getState() {
        return FState;
    }

    public String getMemoryName() {
        return NGGameEngineConstants.CMP_MAIN_MEMORY;
    }

    public void FinishLevel() {
        DoFinishLevel();
        try {
            DoNextLevel();
        }
        finally {
            DoStartLevel();
        }
    }

    public void RestartLevel() {
        raiseLevelFinishEvent();
        DeactivateAllMimicActions();
        DoStartLevel();
    }

    public void BeginUpdate() {
        FUpdateCount = FUpdateCount + 1;
    }

    public void EndUpdate() {
        FUpdateCount = FUpdateCount - 1;
        if (FUpdateCount < 0) {
            FUpdateCount = 0;
        }
    }

    public Boolean InUpdate() {
        return FUpdateCount > 0;
    }

    public void refreshMemoryCell(NGGameEngineMemoryAddress aAddress) {
        getMemoryManager().refreshCell(getMemoryName(), aAddress);
    }

    public void Shutdown() {
        Platform.exit();
    }

    public void ActivateMimicActions(NGCustomControlMimic.Kind aKind) {
        NGControlMimicManager cmm = getMimicManager();
        if (cmm != null) {
            getMimicManager().ActivateMimics(aKind);
        }
    }

    public void ActivateMimicActions(String aType) {
        NGControlMimicManager cmm = getMimicManager();
        if (cmm != null) {
            getMimicManager().ActivateMimics(aType);
        }
    }

    public void ActivateMimicAction(String aName) {
        NGControlMimicManager cmm = getMimicManager();
        if (cmm != null) {
            getMimicManager().ActivateMimic(aName);
        }
    }

    public void DeactivateMimicAction(String aName) {
        NGControlMimicManager cmm = getMimicManager();
        if (cmm != null) {
            getMimicManager().DeactivateMimic(aName);
        }
    }

    public void DeactivateMimicActions(String aType) {
        NGControlMimicManager cmm = getMimicManager();
        if (cmm != null) {
            getMimicManager().DeactivateMimics(aType);
        }
    }

    public void ActivateAllMimicActions() {
        NGControlMimicManager cmm = getMimicManager();
        if (cmm != null) {
            getMimicManager().ActivateAllMimics();
        }
    }

    public void DeactivateAllMimicActions() {
        NGControlMimicManager cmm = getMimicManager();
        if (cmm != null) {
            getMimicManager().DeactivateAllMimics();
        }
    }

    public void ResetAllMimicActions() {
        NGControlMimicManager cmm = getMimicManager();
        if (cmm != null) {
            getMimicManager().ResetAllMimics();
        }
    }

    public void ExecuteGameObject(String aName) {
        NGCustomGameObject obj = getGameObject(aName);
        DoExecuteGameObject(obj);
    }

    @Override
    public void handleTick(NGTickEvent e) {
        DoHandleTick(e);
    }

    @Override
    public void registerObject(String aName, Object aObject) {
        NGGameEngine ge = (NGGameEngine)ResolveObject(NGGameEngine.class);
        ge.registerObject(aName, aObject);
    }

    @Override
    public void unregisterObject(String aName, Object aObject) {
        NGGameEngine ge = (NGGameEngine)ResolveObject(NGGameEngine.class);
        ge.unregisterObject(aName, aObject);
    }

}
