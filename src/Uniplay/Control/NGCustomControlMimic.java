package Uniplay.Control;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Misc.NGTaskManager;
import Uniplay.NGGameEngineConstants;
import Uniplay.Physics.NGObjectPhysicsProcessor;
import Uniplay.Storage.NGCustomGame;
import Uniwork.Misc.NGLogManager;

public abstract class NGCustomControlMimic extends NGUniplayObject {

    public enum Kind {permanent, temporary}

    protected String FName;
    protected Boolean FActive;
    protected NGControlMimicManager FManager;
    protected NGLogManager FLogManager;
    protected NGCustomGame FGame;
    protected Kind FKind;
    protected Boolean FInitialized;
    protected NGObjectPhysicsProcessor FPhysicsProcessor;
    protected NGTaskManager FTaskManager;

    protected void writeLog(String aText) {
        writeLog(0, aText);
    }

    protected void writeLog(int aLogLevel, String aText) {
        if (FLogManager != null) {
            FLogManager.writeLog(aLogLevel, aText, getClass().getName());
        }
    }

    protected void writeError(String aMethodName, String aErrorText) {
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_DEFAULT, String.format("<<<ERROR>>> at [%s.%s] with exception [%s]!", getClass().getName(), aMethodName, aErrorText));
    }

    protected void writeWarning(String aMethodName, String aWarningText) {
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_DEFAULT, String.format("<<<WARNING>>> at [%s.%s] with exception [%s]!", getClass().getName(), aMethodName, aWarningText));
    }

    protected void DoReset() {

    }

    protected void DoActivate() {

    }

    protected void Activate() {
        DoActivate();
    }

    protected void DoDeactivate() {

    }

    protected void Deactivate() {
        DoDeactivate();
    }

    protected void DoInitialize() {

    }

    protected NGTaskManager getTaskManager() {
        if (FTaskManager == null) {
            FTaskManager = (NGTaskManager)ResolveObject(NGGameEngineConstants.CMP_TASK_MANAGER, NGTaskManager.class);
        }
        return FTaskManager;
    }

    protected NGObjectPhysicsProcessor getPhysicsProcessor() {
        if (FPhysicsProcessor == null) {
            FPhysicsProcessor = (NGObjectPhysicsProcessor)ResolveObject(NGGameEngineConstants.CMP_PHYSICS_PROCESSOR, NGObjectPhysicsProcessor.class);
        }
        return FPhysicsProcessor;
    }

    @Override
    protected Object DoResolveObject(String aName, Class aClass) {
        Object result = super.DoResolveObject(aName, aClass);
        if (result == null) {
            return FManager.ResolveObject(aName, aClass);
        }
        return result;
    }

    public NGCustomControlMimic(NGControlMimicManager aManager, NGCustomGame aGame, String aName, Kind aKind) {
        super();
        FManager = aManager;
        FGame = aGame;
        if (FManager != null) {
            FLogManager = FManager.getLogManager();
        }
        FName = aName;
        FKind = aKind;
        FActive = false;
        FInitialized = false;
        Type = "";
        FPhysicsProcessor = null;
        FTaskManager = null;
    }

    public String getName() {
        return FName;
    }

    public NGControlMimicManager getManager() {
        return FManager;
    }

    public NGCustomGame getGame() {
        return FGame;
    }

    public void setActive(Boolean aActive) {
        FActive = aActive;
        if (FActive) {
            Activate();
        }
        else {
            Deactivate();
        }
    }

    public void toggleActive() {
        setActive(!getActive());
    }

    public Boolean getActive() {
        return FActive;
    }

    public void reset() {
        DoReset();
    }

    public Kind getKind() {
        return FKind;
    }

    public void Initialize() {
        if (!FInitialized) {
            DoInitialize();
            FInitialized = true;
        }
    }

    public Boolean InUpdate() {
        return FManager.InUpdate() || FGame.InUpdate();
    }

    public void BeginUpdate() {
        FGame.BeginUpdate();
    }

    public void EndUpdate() {
        FGame.EndUpdate();
    }

    public String Type;

}
