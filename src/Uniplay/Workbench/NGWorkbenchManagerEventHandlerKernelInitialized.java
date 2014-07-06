package Uniplay.Workbench;

import Uniplay.Kernel.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineEventHandlerKernelInitialized;

public class NGWorkbenchManagerEventHandlerKernelInitialized extends NGGameEngineEventHandlerKernelInitialized {

    protected NGWorkbenchManager FManager;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        FManager.newLevelDesigner(NGGameEngineConstants.CMP_WORKBENCH_LEVELDESIGNER);
    }

    public NGWorkbenchManagerEventHandlerKernelInitialized(NGWorkbenchManager aManager) {
        super();
        FManager = aManager;
    }

}
