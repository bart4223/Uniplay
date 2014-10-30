package Uniplay.Control;

import Uniplay.Misc.NGTaskManager;
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

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        addPeriodicTask(Interval);
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

    public NGControlMimicPeriodicAction(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        this(aManager, aGame, aName, Kind.permant);
    }

    public NGControlMimicPeriodicAction(NGControlMimicManager aManager, NGCustomGame aGame, String aName, Kind aKind) {
        super(aManager, aGame, aName, aKind);
        Interval = 0;
    }

    @Override
    public void handleTick(NGTickEvent e) {
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_MIMIC, String.format("Mimic [%s] tick.", getName()));
        if (!InUpdate()) {
            BeginUpdate();
            try {
                DoHandleTick();
            }
            finally {
                EndUpdate();
            }
        }
    }

    public Integer Interval;

}
