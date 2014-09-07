package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGCustomControlMimic;
import Uniplay.Kernel.NGGameEngineMemoryIntegerCellValue;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.NGGameEngineConstants;
import Uniplay.Sound.NGSoundManager;

import java.util.ArrayList;

public abstract class NGCustomGame extends NGUniplayComponent {

    public enum State {Created, Initialized, Started, Hold, Finished};

    protected ArrayList<NGCustomGamePlayerItem> FPlayers;
    protected ArrayList<NGCustomGamePlayerItem> FNPCs;
    protected NGGameManager FManager;
    protected NGPlayerManager FPlayerManager;
    protected NGGameEngineMemoryManager FMemoryManager;
    protected NGControlMimicManager FMimicManager;
    protected State FState;

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
        ActivateMimicActions(NGCustomControlMimic.Kind.permant);
        DoStartLevel();
    }

    protected void DoBeforeStartLevel() {

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

    }

    protected void DoAfterStartLevel() {

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
        for (NGCustomGamePlayerItem item : FPlayers) {
            NGPlayerGameStatistic statistic = getPlayerGameStatistic((NGPlayer)item.getPlayer());
            statistic.incTotal();
            writeLog(statistic.toString());
        }
    }

    protected void DoNextLevel() {

    }

    protected void ActivateMimicActions(NGCustomControlMimic.Kind aKind) {
        getMimicManager().ActivateMimics(aKind);
    }

    protected void ActivateAllMimicActions() {
        getMimicManager().ActivateAllMimics();
    }

    protected void DeactivateAllMimicActions() {
        getMimicManager().DeactivateAllMimics();
    }

    protected void ResetAllMimicActions() {
        getMimicManager().ResetAllMimics();
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

    protected void resetPlayers() {
        for (NGCustomGamePlayerItem item : FPlayers) {
            item.reset();
        }
    }

    protected void addPlayerItem(NGCustomGamePlayerItem aPlayerItem) {
        FPlayers.add(aPlayerItem);
        writeLog(String.format("Player [%s] added in game [%s].", aPlayerItem.getPlayer().getName(), getName()));
    }

    protected void addNPCItem(NGCustomGamePlayerItem aPlayerItem) {
        FNPCs.add(aPlayerItem);
        writeLog(String.format("NPC [%s] added in game [%s].", aPlayerItem.getPlayer().getName(), getName()));
    }

    protected NGSoundManager getSoundManager() {
        return (NGSoundManager)ResolveObject(NGGameEngineConstants.CMP_SOUND_MANAGER, NGSoundManager.class);
    }

    protected Class getMemoryCellValueClass() {
        return NGGameEngineMemoryIntegerCellValue.class;
    }

    protected void raiseGameStartEvent() {
        raiseEvent(NGGameEngineConstants.EVT_GAME_START, new NGCustomGameEventStartGame(this));
    }

    protected void raiseGameBreakEvent() {
        raiseEvent(NGGameEngineConstants.EVT_GAME_BREAK, new NGCustomGameEventBreakGame(this));
    }

    protected void raiseGameContinueEvent() {
        raiseEvent(NGGameEngineConstants.EVT_GAME_CONTINUE, new NGCustomGameEventContinueGame(this));
    }

    protected void raiseGameFinishEvent() {
        raiseEvent(NGGameEngineConstants.EVT_GAME_FINISH, new NGCustomGameEventFinishGame(this));
    }

    public NGCustomGame(NGGameManager aManager, String aName) {
        super(aManager, aName);
        FPlayers = new ArrayList<NGCustomGamePlayerItem>();
        FNPCs = new ArrayList<NGCustomGamePlayerItem>();
        FManager = aManager;
        FPlayerManager = null;
        FState = State.Created;
        getMemoryManager().addMemory(getMemoryName(), 0, 0, 0, getMemoryCellValueClass());
    }

    @Override
    public void Initialize() {
        super.Initialize();
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

    public ArrayList<NGCustomGamePlayerItem> getPlayers() {
        return FPlayers;
    }

    public ArrayList<NGCustomGamePlayerItem> getNPCs() {
        return FNPCs;
    }

    public void removeAllPlayers() {
        FPlayers.clear();
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

    public State getState() {
        return FState;
    }

    public String getMemoryName() {
        return NGGameEngineConstants.CMP_MAIN_MEMORY;
    }

    public void LevelFinished() {
        DeactivateAllMimicActions();
        try {
            DoNextLevel();
        }
        finally {
            ActivateMimicActions(NGCustomControlMimic.Kind.permant);
        }
    }

}
