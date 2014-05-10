package Uniplay.Kernel;

import Uniwork.Base.NGObject;

public class NGGameEngineEventHandler extends NGObject {

    protected String FName;

    protected void DoHandleEvent(NGGameEngineEvent aEvent) {

    }

    public NGGameEngineEventHandler(String aName) {
        super();
        FName = aName;
    }

    public void handleEvent(NGGameEngineEvent aEvent) {
        DoHandleEvent(aEvent);
    }

    public String getName() {
        return FName;
    }

}
