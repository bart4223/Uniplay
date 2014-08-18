package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.NGGameEngineConstants;
import Uniplay.Sound.NGSoundManager;
import Uniwork.Misc.NGLogManager;

import java.util.ArrayList;

public abstract class NGCustomGame extends NGUniplayObject {

    public enum State {Created, Initialized, Started, Hold, Finished};

    protected ArrayList<NGCustomGamePlayerItem> FPlayers;
    protected ArrayList<NGCustomGamePlayerItem> FNPCs;
    protected String FName;
    protected NGGameManager FManager;
    protected NGPlayerManager FPlayerManager;
    protected NGGameEngineMemoryManager FMemoryManager;
    protected NGControlMimicManager FMimicManager;
    protected NGLogManager FLogManager;
    protected State FState;

    protected void DoShowStages() {

    }

    protected void writeLog(String aText) {
        writeLog(0, aText);
    }

    protected void writeLog(int aLogLevel, String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, aText, getClass().getName());
        }
    }

    protected void writeError(String aMethodName, String aErrorText) {
        writeLog(0, String.format("<<<ERROR>>> at [%s.%s] with exception [%s]!", getClass().getName(), aMethodName, aErrorText));
    }

    protected void writeWarning(String aMethodName, String aWarningText) {
        writeLog(0, String.format("<<<WARNING>>> at [%s.%s] with exception [%s]!", getClass().getName(), aMethodName, aWarningText));
    }

    @Override
    protected Object DoResolveObject(String aName, Class aClass) {
        Object result = super.DoResolveObject(aName, aClass);
        if (result == null && FManager != null) {
            result = FManager.ResolveObject(aName, aClass);
        }
        return result;
    }

    protected void DoBeforeStart() {
        ResetAllMimicActions();
    }

    protected void DoStart() {
    }

    protected void DoAfterStart() {
        collectPlayerStatistic();
        ActivateAllMimicActions();
    }

    protected void DoBreak() {

    }

    protected void DoContinue() {

    }

    protected void DoFinish() {
        DeactivateAllMimicActions();
    }

    protected void collectPlayerStatistic() {
        for (NGCustomGamePlayerItem item : FPlayers) {
            NGPlayerGameStatistic statistic = getPlayerGameStatistic((NGPlayer)item.getPlayer());
            statistic.incTotal();
            writeLog(statistic.toString());
        }
    }

    protected void registerMimicActions() {

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

    protected void DoInitialize() {
        registerMimicActions();
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

    public NGCustomGame(NGGameManager aManager, String aName) {
        super();
        FPlayers = new ArrayList<NGCustomGamePlayerItem>();
        FNPCs = new ArrayList<NGCustomGamePlayerItem>();
        FManager = aManager;
        FName = aName;
        FLogManager = null;
        FPlayerManager = null;
        FState = State.Created;
        getMemoryManager().addMemory(getMemoryName(), 0, 0, 0);
    }

    public void Initialize() {
        DoInitialize();
        FState = State.Initialized;
    }

    public NGGameManager getManager() {
        return FManager;
    }

    public String getName() {
        return FName;
    }

    public void setLogManager(NGLogManager aLogManager) {
        FLogManager = aLogManager;
    }

    public NGLogManager getLogManager() {
        return FLogManager;
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

}
