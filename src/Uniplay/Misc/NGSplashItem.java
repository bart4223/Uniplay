package Uniplay.Misc;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGPropertyList;
import Uniwork.Misc.NGLogManager;
import Uniwork.Visuals.NGDisplayController;
import javafx.application.Platform;

import static java.lang.Thread.sleep;

public class NGSplashItem extends NGUniplayObject {

    protected String FName;
    protected NGSplashManager FManager;
    protected NGPropertyList FProps;

    protected void registerDisplayController(NGDisplayController aDisplayController) {
        FManager.registerDisplayController(aDisplayController);
    }

    protected void RunFinished() {

    }

    protected void InternalRun() {
        for (int i = 0; i < InternalRunCount; i++) {
            if (!getFinished()) {
                DoRun();
                RenderScene();
                AfterRun();
            }
        }
    }

    protected void DoRun() {
    }

    protected void InitRun() {
    }

    protected void AfterRun() {

    }

    protected void RenderScene() {
        getManager().RenderScene();
    }

    protected static void runThread(final NGSplashItem aSplashItem) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                aSplashItem.InternalRun();
                if (!aSplashItem.getFinished()) {
                    try {
                        sleep(aSplashItem.Interval);
                    } catch (Exception e) {

                    }
                    runThread(aSplashItem);
                }
                else  {
                    aSplashItem.RunFinished();
                }
            }
        });
    }

    protected void DoInitialize() {

    }

    protected void DoFinish() {

    }

    protected NGLogManager getLogManager() {
        return getManager().getLogManager();
    }

    public NGSplashItem(NGSplashManager aManager, String aName) {
        super();
        FName = aName;
        FManager = aManager;
        FProps = new NGPropertyList();
        Interval = 5;
        WaitTimeAfterFinish = 5;
        InternalRunCount = 5;
        ControllerObjectName = "Layer1";
        Filename = "";
        WaitTimeAfterFinish = 1000;
    }

    public void Run() {
        runThread(this);
    }

    public void Initialize() {
        DoInitialize();
    }

    public void Finish() {
        DoFinish();
    }

    public NGSplashManager getManager() {
        return FManager;
    }

    public NGPropertyList getProps() {
        return FProps;
    }

    public Boolean getFinished() {
        return true;
    }

    public String getName() {
        return FName;
    }

    public Integer Interval;

    public String ControllerObjectName;

    public String Filename;

    public Integer WaitTimeAfterFinish;

    public Integer InternalRunCount;

}
