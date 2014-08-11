package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicPeriodicAction;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.NGGameEngineConstants;
import Uniwork.Misc.NGLogManager;

public abstract class NGCustomGame extends NGUniplayObject {

    public enum State {Created, Started, Hold, Finished};

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
        registerMimicActions();
    }

    protected void DoStart() {

    }

    protected void DoAfterStart() {
        ActivateAllMimicActions();
    }

    protected void DoBreak() {

    }

    protected void DoContinue() {

    }

    protected void DoFinish() {

    }

    protected void registerMimicActions() {
    }

    protected void ActivateAllMimicActions() {
        getMimicManager().ActivateAllMimics();
    }

    public NGCustomGame(NGGameManager aManager, String aName) {
        super();
        FManager = aManager;
        FName = aName;
        FLogManager = null;
        FPlayerManager = null;
        FState = State.Created;
        getMemoryManager().addMemory(getMemoryName(), 0, 0, 0);
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
        if (FState == State.Created || FState == State.Finished) {
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
