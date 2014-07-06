package Uniplay.Debugger;

import Uniplay.Kernel.NGGameEngineEventHandlerKernelStarted;

public class NGUniplayConsoleEventHandlerKernelStarted extends NGGameEngineEventHandlerKernelStarted {

    protected NGUniplayConsole FConsole;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        FConsole.showStage();
    }

    public NGUniplayConsoleEventHandlerKernelStarted(NGUniplayConsole aConsole) {
        super();
        FConsole = aConsole;
    }

}
