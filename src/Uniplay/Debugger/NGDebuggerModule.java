package Uniplay.Debugger;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Kernel.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;
import Uniplay.NGGameEngine;
import Uniwork.Misc.NGLogEventListenerManagement;

public class NGDebuggerModule extends NGGameEngineModule {

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGUniplayComponent component = new NGUniplayConsole(this, NGGameEngineConstants.CMP_CONSOLE);
        addSubComponent(component);
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        NGLogEventListenerManagement lelr = (NGLogEventListenerManagement)ResolveObject(NGLogEventListenerManagement.class);
        NGUniplayConsole console = getConsole();
        console.writeLog(lelr.getCompleteLog(NGUniplayConsole.FMT_DATETIME));
        console.setShowLogEntrySource(((NGGameEngine)ResolveObject(NGGameEngine.class)).getConsoleShowLogEntrySource());
        lelr.addLogListener(console);

    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        getConsole().setStagePosition(1400, 1100);
    }

    protected NGUniplayConsole getConsole() {
        NGUniplayConsole console = (NGUniplayConsole)ResolveObject(NGGameEngineConstants.CMP_CONSOLE, NGUniplayConsole.class);
        return console;
    }


    public NGDebuggerModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
