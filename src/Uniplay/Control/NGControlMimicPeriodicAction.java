package Uniplay.Control;

import Uniplay.Misc.NGTaskCallback;
import Uniplay.Misc.NGTaskManager;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.NGCustomGame;
import Uniwork.Misc.NGTickEvent;
import Uniwork.Misc.NGTickListener;

public abstract class NGControlMimicPeriodicAction extends NGCustomControlMimic implements NGTickListener {

    private class CallbackPeriodicAction implements NGTaskCallback {

        protected NGControlMimicPeriodicAction FMimicAction;

        public CallbackPeriodicAction(NGControlMimicPeriodicAction aMimicAction) {
            FMimicAction = aMimicAction;
        }

        @Override
        public synchronized void Call() {
            FMimicAction.DoHandleTick();
        }
    }

    protected void DoHandleTick() {

    }

    @Override
    protected void DoActivate() {
        super.DoActivate();
        if (Interval > 0) {
            getTaskManager().startPeriodicTask(getPeriodicTaskName(), Delay);
        }
        else {
            getTaskManager().startSingularTask(new CallbackPeriodicAction(this), Delay);
        }
    }

    @Override
    protected void DoDeactivate() {
        super.DoDeactivate();
        if (Interval > 0) {
            getTaskManager().stopPeriodicTask(getPeriodicTaskName());
        }
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        if (Interval > 0) {
            addPeriodicTask(Interval);
        }
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
        this(aManager, aGame, aName, Kind.permanent);
    }

    public NGControlMimicPeriodicAction(NGControlMimicManager aManager, NGCustomGame aGame, String aName, Kind aKind) {
        super(aManager, aGame, aName, aKind);
        Interval = 0;
        Delay = 0;
    }

    @Override
    public synchronized void handleTick(NGTickEvent e) {
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
    public Integer Delay;

}
