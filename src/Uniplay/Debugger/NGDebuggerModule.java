package Uniplay.Debugger;

import Uniplay.Kernel.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineLoggingManagement;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;
import Uniwork.Misc.NGLogEventListenerRegistration;

public class NGDebuggerModule extends NGGameEngineModule {

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGUniplayConsole console = (NGUniplayConsole)new NGUniplayConsole(this, NGGameEngineConstants.CMP_CONSOLE);
        addSubComponent(console);
        registerEventHandler(new NGUniplayConsoleEventHandlerKernelStarted(console));
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        NGUniplayConsole console = getConsole();
        NGLogEventListenerRegistration lelr = (NGLogEventListenerRegistration)ResolveObject(NGLogEventListenerRegistration.class);
        lelr.addLogListener(console);
        NGGameEngineLoggingManagement logManagement = (NGGameEngineLoggingManagement)ResolveObject(NGGameEngineLoggingManagement.class);
        console.writeLog(logManagement.getCompleteLog(NGUniplayConsole.FMT_DATETIME));
        console.setShowLogEntrySource(logManagement.getShowLogEntrySource());
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        getConsole().setStagePosition(500, 1100);
    }

    protected NGUniplayConsole getConsole() {
        NGUniplayConsole console = (NGUniplayConsole)ResolveObject(NGGameEngineConstants.CMP_CONSOLE, NGUniplayConsole.class);
        return console;
    }


    public NGDebuggerModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
