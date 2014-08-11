package Uniplay.Control;

import Uniplay.Kernel.NGTaskManager;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.NGCustomGame;
import Uniwork.Misc.NGTickEvent;
import Uniwork.Misc.NGTickListener;

public abstract class NGControlMimicPeriodicAction extends NGCustomControlMimic implements NGTickListener {

    protected void DoHandleTick() {

    }

    @Override
    protected void DoActivate() {
        super.DoActivate();
        getTaskManager().startPeriodicTask(getPeriodicTaskName(), 0);
    }

    @Override
    protected void DoDeactivate() {
        super.DoDeactivate();
        getTaskManager().stopPeriodicTask(getPeriodicTaskName());
    }

    protected void addPeriodicTask(Integer aInterval) {
        getTaskManager().addPeriodicTask(getPeriodicTaskName(), aInterval);
        getTaskManager().addListener(getPeriodicTaskName(), this);
    }

    protected String getPeriodicTaskName() {
        return String.format("Mimic.%s", getName());
    }

    protected NGTaskManager getTaskManager() {
        return FManager.getTaskManager();
    }

    public NGControlMimicPeriodicAction(NGControlMimicManager aManager, NGCustomGame aGame, String aName, Integer aInterval) {
        super(aManager, aGame, aName);
        addPeriodicTask(aInterval);
    }

    @Override
    public void handleTick(NGTickEvent e) {
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_MIMIC, String.format("Mimic [%s] tick.", getName()));
        DoHandleTick();
    }

}
