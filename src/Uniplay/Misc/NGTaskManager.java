package Uniplay.Misc;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniwork.Misc.NGTickGenerator;
import Uniwork.Misc.NGTickListener;

import java.util.Timer;
import java.util.TimerTask;

public class NGTaskManager extends NGUniplayComponent {

    protected NGTickGenerator FTickGenerator;
    protected Timer FTimer;

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FTickGenerator.Initialize();
    }

    @Override
    protected void DoFinalize() {
        super.DoFinalize();
        FTickGenerator.Finalize();
        FTimer.cancel();
    }

    public NGTaskManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FTickGenerator = new NGTickGenerator();
        FTimer = new Timer();
    }

    public synchronized void addListener(String aName, NGTickListener aListener)  {
        FTickGenerator.addListener(aName, aListener);
    }

    public synchronized void removeListener(String aName, NGTickListener aListener)   {
        FTickGenerator.removeListener(aName, aListener);
    }

    public void removePeriodicTask(String aName) {
        FTickGenerator.DeleteItem(aName);
    }

    public void addPeriodicTask(String aName, Integer aInterval) {
        FTickGenerator.NewItem(aName, aInterval);
    }

    public void startPeriodicTask(String aName) {
        startPeriodicTask(aName, 0);
    }

    public void startPeriodicTask(String aName, Integer aDelay) {
        FTickGenerator.SetItemEnabled(aName, true, aDelay);
    }

    public void stopPeriodicTask(String aName) {
        FTickGenerator.SetItemEnabled(aName, false);
    }

    public void stopAllPeriodicTasks() {
        FTickGenerator.SetAllEnabled(false);
    }

    public void startSingularTask(final NGTaskCallback aCallback, Integer aDelay) {
        TimerTask lTimerTask = new TimerTask() {
            public void run() {
                synchronized (this) {
                    aCallback.Call();
                }
            }
        };
        FTimer.schedule(lTimerTask, aDelay);
    }

    public void setTaskProp(String aName, String aPropName, Object aObject) {
        FTickGenerator.setItemProp(aName, aPropName, aObject);
    }

    public Object getTaskProp(String aName, String aPropName) {
        return FTickGenerator.getItemProp(aName, aPropName);
    }

    public void setGeneratorEnabled(Boolean aValue) {
        FTickGenerator.setEnabled(aValue);
    }

    public Boolean getGeneratorEnabled() {
        return FTickGenerator.getEnabled();
    }

    public void ToggleGeneratorEnabled() {
        FTickGenerator.ToggleEnabled();
    }

}
