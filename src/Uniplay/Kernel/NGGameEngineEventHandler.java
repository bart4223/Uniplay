package Uniplay.Kernel;

import Uniwork.Base.NGObject;

public class NGGameEngineEventHandler extends NGObject {

    protected String FName;
    protected NGGameEngineEvent FEvent;

    protected void BeforeHandleEvent() {

    }

    protected void DoHandleEvent() {

    }

    protected void AfterHandleEvent() {

    }

    protected void InternalhandleEvent() {
        BeforeHandleEvent();
        DoHandleEvent();
        AfterHandleEvent();
    }

    public NGGameEngineEventHandler(String aName) {
        super();
        FName = aName;
    }

    public void handleEvent(NGGameEngineEvent aEvent) {
        FEvent = aEvent;
        try {
            InternalhandleEvent();
        }
        finally {
            FEvent = null;
        }
    }

    public String getName() {
        return FName;
    }

}
