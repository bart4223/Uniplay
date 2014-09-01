package Uniplay.Control;

import Uniplay.Base.NGUniplayObject;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.NGCustomGame;
import Uniwork.Misc.NGLogManager;

public abstract class NGCustomControlMimic extends NGUniplayObject {

    public enum Kind {permant, temporary};

    protected String FName;
    protected Boolean FActive;
    protected NGControlMimicManager FManager;
    protected NGLogManager FLogManager;
    protected NGCustomGame FGame;
    protected Kind FKind;
    protected Boolean FInitialized;

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

}
