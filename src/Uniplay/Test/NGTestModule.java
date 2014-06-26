package Uniplay.Test;

import Uniplay.Kernel.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;
import Uniwork.Base.NGObjectRequestRegistration;
import Uniwork.Misc.NGTickEvent;
import Uniwork.Misc.NGTickGenerator;
import Uniwork.Misc.NGTickListener;

public class NGTestModule extends NGGameEngineModule implements NGTickListener{

    public static final String TICK_SPEEDTEST = "SPEEDTEST";

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        int size = 64;
        NGGameEngineMemoryManager manager = (NGGameEngineMemoryManager)ResolveObject(NGGameEngineConstants.CMP_MEMORY_MANAGER, NGGameEngineMemoryManager.class);
        manager.addMemory(NGGameEngineConstants.CMP_MAIN_MEMORY, 1, size, size);
        manager.clearMemory(NGGameEngineConstants.CMP_MAIN_MEMORY);
        NGTickGenerator tick = (NGTickGenerator)ResolveObject(NGGameEngineConstants.OBJ_TICKGENERATOR, NGTickGenerator.class);
        tick.NewItem(TICK_SPEEDTEST, 5);
        tick.addListener(TICK_SPEEDTEST, this);
        //tick.SetItemEnabled(TICK_SPEEDTEST, true);
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

}
