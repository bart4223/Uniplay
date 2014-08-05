package Uniplay.Test;

import Uniplay.Kernel.*;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.NGPlayerManager;
import Uniwork.Base.NGObjectRequestRegistration;
import Uniwork.Misc.NGTickEvent;
import Uniwork.Misc.NGTickListener;

public class NGTestModule extends NGGameEngineModule implements NGTickListener {

    private class Callback implements NGTaskCallback {

        protected NGTestModule FModule;

        public Callback(NGTestModule aModule) {
            FModule = aModule;
        }

        @Override
        public void Call() {
            FModule.incAllMainMemoryCells();
        }
    }

    public static final String TICK_SPEEDTEST = "SPEEDTEST";

    @Override
    protected void registerEventHandlers() {
        super.registerEventHandlers();
        registerEventHandler(new NGTestModuleEventHandlerKernelInitialized(this));
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        int size = 64;
        NGGameEngineMemoryManager memory = (NGGameEngineMemoryManager)ResolveObject(NGGameEngineConstants.CMP_MEMORY_MANAGER, NGGameEngineMemoryManager.class);
        memory.addMemory(NGGameEngineConstants.CMP_MAIN_MEMORY, 1, size, size);
        memory.clearMemory(NGGameEngineConstants.CMP_MAIN_MEMORY);
        NGTaskManager task = (NGTaskManager)ResolveObject(NGGameEngineConstants.CMP_TASK_MANAGER, NGTaskManager.class);
        task.addPeriodicTask(TICK_SPEEDTEST, 5);
        task.addListener(TICK_SPEEDTEST, this);
        //task.startPeriodicTask(TICK_SPEEDTEST, 200);
        //task.startSingularTask(new Callback(this), 1000);
        NGObjectRequestRegistration orr = (NGObjectRequestRegistration)ResolveObject(NGObjectRequestRegistration.class);
        orr.registerObjectRequest("TestModule", this, "Test", "incAllMainMemoryCells");
    }

    public NGTestModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

    @Override
    public synchronized void handleTick(NGTickEvent e) {
        if (e.Name.equals(TICK_SPEEDTEST)) {
            NGGameEngineMemoryManager manager = (NGGameEngineMemoryManager)ResolveObject(NGGameEngineConstants.CMP_MEMORY_MANAGER, NGGameEngineMemoryManager.class);
            manager.incAllMemoryCellsValue(NGGameEngineConstants.CMP_MAIN_MEMORY);
        }
    }

    public void incAllMainMemoryCells() {
        NGGameEngineMemoryManager manager = (NGGameEngineMemoryManager)ResolveObject(NGGameEngineConstants.CMP_MEMORY_MANAGER, NGGameEngineMemoryManager.class);
        manager.incAllMemoryCellsValue(NGGameEngineConstants.CMP_MAIN_MEMORY);
    }

    public void addTestPlayers() {
        NGPlayerManager manager = (NGPlayerManager)ResolveObject(NGGameEngineConstants.CMP_PLAYER_MANAGER, NGPlayerManager.class);
        manager.newPlayer("BART4223", "Bart4223");
        manager.newPlayer("SEPPI", "Seppi");
        manager.newPlayer("XMEN", "XMen");
        manager.setCurrentPlayer("BART4223");
    }

}
