package Uniplay.Test;

import Uniplay.Kernel.*;
import Uniplay.Misc.NGTaskCallback;
import Uniplay.NGGameEngineConstants;
import Uniplay.Sound.NGSoundManager;
import Uniwork.Base.NGObjectQueueManager;
import Uniwork.Base.NGObjectRequestRegistration;
import Uniwork.Base.NGObjectStackManager;
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

    protected NGObjectStackManager FStackManager;
    protected NGObjectQueueManager FQueueManager;

    @Override
    protected void registerEventHandlers() {
        super.registerEventHandlers();
        registerEventHandler(new NGTestModuleEventHandlerKernelInitialized(this));
        registerEventHandler(new NGTestModuleCommonEventHandler());
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        /*
        int size = 64;
        NGGameEngineMemoryManager memory = (NGGameEngineMemoryManager)ResolveObject(NGGameEngineConstants.CMP_MEMORY_MANAGER, NGGameEngineMemoryManager.class);
        memory.addMemory(NGGameEngineConstants.CMP_MAIN_MEMORY, 1, size, size);
        memory.clearMemory(NGGameEngineConstants.CMP_MAIN_MEMORY);
        NGTaskManager task = (NGTaskManager)ResolveObject(NGGameEngineConstants.CMP_TASK_MANAGER, NGTaskManager.class);
        task.addPeriodicTask(TICK_SPEEDTEST, 5);
        task.addListener(TICK_SPEEDTEST, this);
        //task.startPeriodicTask(TICK_SPEEDTEST, 200);
        //task.startSingularTask(new Callback(this), 1000);
        */
        FStackManager = new NGObjectStackManager();
        FStackManager.addStack("STACK");
        FQueueManager = new NGObjectQueueManager();
        FQueueManager.addQueue("QUEUE");
        NGObjectRequestRegistration orr = (NGObjectRequestRegistration)ResolveObject(NGObjectRequestRegistration.class);
        orr.registerObjectRequest("TestModule", this, "Test", "Test");
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

    public void Test() {
        stopAllSounds();
        TestObjectStack();
        TestObjectQueue();
    }

    public void incAllMainMemoryCells() {
        NGGameEngineMemoryManager manager = (NGGameEngineMemoryManager)ResolveObject(NGGameEngineConstants.CMP_MEMORY_MANAGER, NGGameEngineMemoryManager.class);
        manager.incAllMemoryCellsValue(NGGameEngineConstants.CMP_MAIN_MEMORY);
    }

    public void TestObjectStack() {
        FStackManager.pushStack("STACK", "1", 1);
        FStackManager.pushStack("STACK", "2", 2);
        FStackManager.pushStack("STACK", "3", 3);
        while (!FStackManager.isStackEmpty("STACK")) {
            Integer value = (Integer)FStackManager.popStack("STACK");
            //Integer value = (Integer)FStackManager.popStack("STACK", "2");
            if (value != null) {
                writeLog(String.format("Stack: %d", value));
            }
        }
    }

    public void TestObjectQueue() {
        FQueueManager.enterQueue("QUEUE", "1", 1);
        FQueueManager.enterQueue("QUEUE", "2", 2);
        FQueueManager.enterQueue("QUEUE", "3", 3);
        while (!FQueueManager.isQueueEmpty("QUEUE")) {
            Integer value = (Integer)FQueueManager.leaveQueue("QUEUE");
            //Integer value = (Integer)FQueueManager.leaveQueue("QUEUE", "2");
            if (value != null) {
                writeLog(String.format("Queue: %d", value));
            }
        }
    }

    public void stopAllSounds() {
        NGSoundManager manager = (NGSoundManager)ResolveObject(NGGameEngineConstants.CMP_SOUND_MANAGER, NGSoundManager.class);
        if (manager != null) {
            manager.stopAllSounds();
        }
    }

    public void handleKernelInitialized() {

    }

}
