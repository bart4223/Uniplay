package Uniplay.Kernel;

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

    protected void InternalRun() {
        for (int i = 0; i < 32; i++) {
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
            }
        });
    }

    protected void DoInitialize() {

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
    }

    public void Run() {
        runThread(this);
    }

    public void Initialize() {
        DoInitialize();
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

}
