package Uniplay.Test;

import Uniplay.Kernel.*;
import Uniplay.NGGameEngineConstants;
import Uniplay.Sound.NGMediaPlayerSoundItem;
import Uniplay.Sound.NGSoundItem;
import Uniplay.Sound.NGSoundManager;
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
        NGObjectRequestRegistration orr = (NGObjectRequestRegistration)ResolveObject(NGObjectRequestRegistration.class);
        orr.registerObjectRequest("TestModule", this, "Test", "Test");
    }

    protected void addTestPlayers() {
        NGPlayerManager manager = (NGPlayerManager)ResolveObject(NGGameEngineConstants.CMP_PLAYER_MANAGER, NGPlayerManager.class);
        manager.newPlayer("BART4223", "Bart4223");
        manager.newPlayer("SEPPI", "Seppi");
        manager.newPlayer("XMEN", "XMen");
        manager.newPlayer("SKYGENERATION", "Sky");
        manager.setCurrentPlayer("BART4223");
    }

    protected void playSound() {
        NGSoundManager manager = (NGSoundManager)ResolveObject(NGGameEngineConstants.CMP_SOUND_MANAGER, NGSoundManager.class);
        if (manager != null) {
            NGSoundItem item = manager.addSound("Source", "resources/sound/source.mp3");
            manager.playSound(item, NGMediaPlayerSoundItem.Mode.singular);
        }
        else
            writeLog("No sound manager available!");
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
    }

    public void incAllMainMemoryCells() {
        NGGameEngineMemoryManager manager = (NGGameEngineMemoryManager)ResolveObject(NGGameEngineConstants.CMP_MEMORY_MANAGER, NGGameEngineMemoryManager.class);
        manager.incAllMemoryCellsValue(NGGameEngineConstants.CMP_MAIN_MEMORY);
    }

    public void stopAllSounds() {
        NGSoundManager manager = (NGSoundManager)ResolveObject(NGGameEngineConstants.CMP_SOUND_MANAGER, NGSoundManager.class);
        if (manager != null) {
            manager.stopAllSounds();
        }
    }

    public void handleKernelInitialized() {
        addTestPlayers();
        //playSound();
    }

}
