package Uniplay.Control;

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
        getManager().getTaskManager().startPeriodicTask(getPeriodicTaskName(), 0);
    }

    @Override
    protected void DoDeactivate() {
        super.DoDeactivate();
        getManager().getTaskManager().stopPeriodicTask(getPeriodicTaskName());
    }

    protected void addPeriodicTask(Integer aInterval) {
        getManager().getTaskManager().addPeriodicTask(getPeriodicTaskName(), aInterval);
        getManager().getTaskManager().addListener(getPeriodicTaskName(), this);
    }

    protected String getPeriodicTaskName() {
        return String.format("Mimic.%s", getName());
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
