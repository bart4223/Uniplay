package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Base.NGUniplayObjectPhysicsProperties;
import Uniwork.Misc.NGLogManager;

public abstract class NGCustomGameObject extends NGUniplayObject {

    protected NGLogManager FLogManager;
    protected NGCustomGame FGame;
    protected NGUniplayObjectPhysicsProperties FPhysics;
    protected String FName;

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

    protected void DoReset() {

    }

    protected void DoExecute() {

    }

    public NGCustomGameObject(NGCustomGame aGame) {
        this(aGame, "");
    }

    public NGCustomGameObject(NGCustomGame aGame, String aName) {
        super();
        FGame = aGame;
        FLogManager = FGame.getLogManager();
        FPhysics = new NGUniplayObjectPhysicsProperties();
        FPhysics.Mass = 0.0;
        FName = aName;
    }

    public String getName() {
        return FName;
    }

    public NGCustomGame getGame() {
        return FGame;
    }

    public double getMass() {
        return FPhysics.Mass;
    }

    public void Reset() {
        DoReset();
        writeLog(String.format("Game object [%s] from game [%s] reset.", getName(), FGame.getName()));
    }

    public void Execute() {
        DoExecute();
    }

}
