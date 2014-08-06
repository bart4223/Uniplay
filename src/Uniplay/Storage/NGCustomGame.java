package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Misc.NGLogManager;

public abstract class NGCustomGame extends NGUniplayObject {

    protected String FName;
    protected NGGameManager FManager;
    protected NGLogManager FLogManager;

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

    public NGCustomGame(NGGameManager aManager, String aName) {
        super();
        FManager = aManager;
        FName = aName;
        FLogManager = null;
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

    public void showStages() {
        DoShowStages();
    }

}
