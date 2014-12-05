package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGCustomControlMimic;
import Uniplay.Kernel.*;
import Uniplay.Misc.NGTaskManager;
import Uniplay.NGGameEngineConstants;
import Uniplay.Sound.NGSoundManager;
import Uniwork.Misc.NGTickEvent;
import Uniwork.Misc.NGTickListener;
import javafx.application.Platform;

import java.util.ArrayList;

public abstract class NGCustomGame extends NGUniplayComponent implements NGTickListener {

    public enum State {Created, Initialized, Started, Hold, Finished};

    protected ArrayList<NGCustomGameCharacter> FPCs;
    protected ArrayList<NGCustomGameCharacter> FNPCs;
    protected NGGameManager FManager;
    protected NGPlayerManager FPlayerManager;
    protected NGGameEngineMemoryManager FMemoryManager;
    protected NGControlMimicManager FMimicManager;
    protected State FState;
    protected Integer FUpdateCount;

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
        ActivateMimicActions(NGCustomControlMimic.Kind.permant);
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
            pc.reset();
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

    public NGCustomGame(NGGameManager aManager, String aName) {
        super(aManager, aName);
        FPCs = new ArrayList<NGCustomGameCharacter>();
        FNPCs = new ArrayList<NGCustomGameCharacter>();
        FManager = aManager;
        FPlayerManager = null;
        FState = State.Created;
        getMemoryManager().addMemory(getMemoryName(), 0, 0, 0, getMemoryCellValueClass());
        FUpdateCount = 0;
    }

    @Override
    public void DoInitialized() {
        super.DoInitialized();
        FState = State.Initialized;
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

    public ArrayList<NGCustomGameCharacter> getPCs() {
        return FPCs;
    }

    public ArrayList<NGCustomGameCharacter> getNPCs() {
        return FNPCs;
    }

    public void removeAllPCs() {
        FPCs.clear();
    }

    public void removeAllNPCs() {
        FNPCs.clear();
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
        getMimicManager().ActivateMimics(aKind);
    }

    public void ActivateMimicActions(String aType) {
        getMimicManager().ActivateMimics(aType);
    }

    public void ActivateMimicAction(String aName) {
        getMimicManager().ActivateMimic(aName);
    }

    public void DeactivateMimicAction(String aName) {
        getMimicManager().DeactivateMimic(aName);
    }

    public void DeactivateMimicActions(String aType) {
        getMimicManager().DeactivateMimics(aType);
    }

    public void ActivateAllMimicActions() {
        getMimicManager().ActivateAllMimics();
    }

    public void DeactivateAllMimicActions() {
        getMimicManager().DeactivateAllMimics();
    }

    public void ResetAllMimicActions() {
        getMimicManager().ResetAllMimics();
    }

    protected void DoHandleTick(NGTickEvent aEvent) {

    }

    @Override
    public void handleTick(NGTickEvent e) {
        DoHandleTick(e);
    }

}
