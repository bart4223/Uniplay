package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;

public abstract class NGGameEngineEventHandler extends NGUniplayObject {

    protected String FName;
    protected String FEventName;
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

    public NGGameEngineEventHandler() {
        this("");
    }

    public NGGameEngineEventHandler(String aName) {
        super();
        FName = aName;
    }

    public void handleEvent(String aEventname, NGGameEngineEvent aEvent) {
        FEvent = aEvent;
        FEventName = aEventname;
        try {
            InternalhandleEvent();
        }
        finally {
            FEvent = null;
            FEventName = "";
        }
    }

    public String getName() {
        return FName;
    }

}
