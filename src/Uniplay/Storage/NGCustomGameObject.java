package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Base.NGUniplayObjectPhysicsProperties;
import Uniwork.Misc.NGLogManager;

public abstract class NGCustomGameObject extends NGUniplayObject {

    protected NGLogManager FLogManager;
    protected NGCustomGame FGame;
    protected NGUniplayObjectPhysicsProperties FPhysics;

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

    public NGCustomGameObject(NGCustomGame aGame) {
        super();
        FGame = aGame;
        FLogManager = FGame.getLogManager();
        FPhysics = new NGUniplayObjectPhysicsProperties();
        FPhysics.Mass = 0.0;
    }

    public NGCustomGame getGame() {
        return FGame;
    }

    public double getMass() {
        return FPhysics.Mass;
    }

}
