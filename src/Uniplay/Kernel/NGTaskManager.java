package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniwork.Misc.NGTickGenerator;
import Uniwork.Misc.NGTickListener;

import java.util.Timer;
import java.util.TimerTask;

public class NGTaskManager extends NGUniplayComponent {

    protected NGTickGenerator FTickGenerator;
    protected Timer FTimer;

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

    public void addPeriodicTask(String aName, Integer aInterval) {
        FTickGenerator.NewItem(aName, aInterval);
    }

    public void startPeriodicTask(String aName, Integer aDelay) {
        FTickGenerator.SetItemEnabled(aName, true, aDelay);
    }

    public void stopPeriodicTask(String aName) {
        FTickGenerator.SetItemEnabled(aName, false);
    }

    public void startSingularTask(Integer aDelay) {
        TimerTask lTimerTask = new TimerTask() {
            public void run() {
                synchronized (this) {
                    //DoTick();
                }
            }
        };
        FTimer.schedule(lTimerTask, aDelay);
    }

}
