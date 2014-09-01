package Uniplay.Control;

import Uniplay.Kernel.NGTaskManager;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.NGCustomGame;
import Uniwork.Misc.NGTickEvent;
import Uniwork.Misc.NGTickListener;

public abstract class NGControlMimicPeriodicAction extends NGCustomControlMimic implements NGTickListener {

    protected Boolean FTaskAdded;

    protected void DoHandleTick() {

    }

    @Override
    protected void DoActivate() {
        super.DoActivate();
        if (!FTaskAdded) {
            addPeriodicTask(Interval);
        }
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
        FTaskAdded = true;
    }

    protected String getPeriodicTaskName() {
        return String.format("Mimic.%s", getName());
    }

    protected NGTaskManager getTaskManager() {
        return FManager.getTaskManager();
    }

    public NGControlMimicPeriodicAction(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        this(aManager, aGame, aName, Kind.permant);
    }

    public NGControlMimicPeriodicAction(NGControlMimicManager aManager, NGCustomGame aGame, String aName, Kind aKind) {
        super(aManager, aGame, aName, aKind);
        Interval = 0;
        FTaskAdded = false;
    }

    @Override
    public void handleTick(NGTickEvent e) {
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_MIMIC, String.format("Mimic [%s] tick.", getName()));
        DoHandleTick();
    }

    public Integer Interval;

}
