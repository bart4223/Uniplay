package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;

public class NGGameEngineEventHandler extends NGUniplayObject {

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
