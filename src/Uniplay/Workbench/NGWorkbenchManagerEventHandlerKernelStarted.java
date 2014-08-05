package Uniplay.Workbench;

import Uniplay.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineEventHandlerKernelStarted;

public class NGWorkbenchManagerEventHandlerKernelStarted extends NGGameEngineEventHandlerKernelStarted {

    protected NGWorkbenchManager FManager;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        FManager.showControlStage();
        FManager.showLevelDesigner(NGGameEngineConstants.CMP_WORKBENCH_LEVELDESIGNER);
    }

    public NGWorkbenchManagerEventHandlerKernelStarted(NGWorkbenchManager aManager) {
        super();
        FManager = aManager;
    }

}
