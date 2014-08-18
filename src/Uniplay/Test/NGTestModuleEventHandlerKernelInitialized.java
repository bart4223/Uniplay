package Uniplay.Test;

import Uniplay.Kernel.NGGameEngineEventHandlerKernelInitialized;

public class NGTestModuleEventHandlerKernelInitialized extends NGGameEngineEventHandlerKernelInitialized {

    protected NGTestModule FModule;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        FModule.handleKernelInitialized();
    }

    public NGTestModuleEventHandlerKernelInitialized(NGTestModule aModule) {
        super();
        FModule = aModule;
    }

}
