package Uniplay.Workbench;

import Uniplay.Kernel.NGGameEngineEventHandlerKernelStarted;

public class NGWorkbenchManagerEventHandlerKernelStarted extends NGGameEngineEventHandlerKernelStarted {

    protected NGWorkbenchManager FManager;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        FManager.showControlStage();
    }

    public NGWorkbenchManagerEventHandlerKernelStarted(NGWorkbenchManager aManager) {
        super();
        FManager = aManager;
    }

}
