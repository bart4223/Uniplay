package Uniplay.Misc;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.NGGameEngineConstants;
import Uniwork.Visuals.NGDisplayController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Thread.sleep;

public class NGSplashManager extends NGUniplayComponent {

    protected Stage FStage;
    protected CopyOnWriteArrayList<NGSplashItem> FItems;
    protected NGSplashStageController FController;

    protected void CreateStage(){
        FStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("NGSplashStage.fxml"));
        try {
            lXMLLoader.load();
            FController = lXMLLoader.getController();
            FController.Manager = this;
            FController.Initialize();
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle("Uniplay.Splash");
            FStage.setScene(new Scene(lRoot, 800, 600, Color.LIGHTGRAY));
            FStage.setResizable(false);
            FStage.initStyle(StageStyle.TRANSPARENT);
        }
        catch( Exception e) {
            writeError("CreateStage", e.getMessage());
        }
    }

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        CreateStage();
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        for (NGSplashItem item : FItems) {
            item.Initialize();
        }
    }

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        NGSplashGeometryObjects item = new NGSplashGeometryObjects(this, "Splash.Uniplay");
        item.GeometryObjectColor = "#ff0000";
        item.Filename = "resources/splash/uniplay.gof";
        addItem(item);
    }

    protected void addItem(NGSplashItem aItem) {
        FItems.add(aItem);
    }

    protected NGSplashItem getItem(Integer aIndex) {
        return FItems.get(aIndex);
    }

    protected static void runThread(final NGSplashManager aSplashManager, final Integer aIndex) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (aSplashManager.FItems.size() > aIndex) {
                    NGSplashItem splashitem = aSplashManager.getItem(aIndex);
                    if (!splashitem.getFinished()) {
                        runThread(aSplashManager, aIndex);
                    }
                    else {
                        aSplashManager.getItem(aIndex).Finish();
                        Integer index = aIndex + 1;
                        if (aSplashManager.FItems.size() > index) {
                            splashitem = aSplashManager.getItem(index);
                            splashitem.InitRun();
                            splashitem.Run();
                            runThread(aSplashManager, index);
                        }
                        else {
                            try {
                                sleep(splashitem.WaitTimeAfterFinish);
                            } catch (Exception e) {
                            }
                            aSplashManager.Finish();
                        }
                    }
                }
            }
        });
    }

    protected void DoRun() {
        if (FItems.size() > 0) {
            NGSplashItem splashitem = getItem(0);
            splashitem.InitRun();
            splashitem.Run();
            runThread(this, 0);
        }
    }

    protected void raiseRunEvent() {
        raiseEvent(NGGameEngineConstants.EVT_KERNEL_SPLASH_RUN, new NGSplashManagerEventRun(this));
    }

    protected void raiseFinishEvent() {
        raiseEvent(NGGameEngineConstants.EVT_KERNEL_SPLASH_FINISH, new NGSplashManagerEventFinish(this));
    }

    public NGSplashManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FItems = new CopyOnWriteArrayList<NGSplashItem>();
        GridDistance = 16;
    }

    public void showStage() {
        FStage.show();
    }

    public void closeStage() {
        FStage.close();
    }

    public void InitRun() {
        for (NGSplashItem item : FItems) {
            item.InitRun();
        }
    }

    public void Run() {
        RenderScene();
        showStage();
        raiseRunEvent();
        DoRun();
    }

    public void Finish() {
        closeStage();
        raiseFinishEvent();
    }

    public Boolean getFinished() {
        for (NGSplashItem item : FItems) {
            if (!item.getFinished())
                return false;
        }
        return true;
    }

    public void registerDisplayController(NGDisplayController aDisplayController) {
        FController.registerDisplayController(aDisplayController);
    }

    public Object getControllerObject(String aName, Class aClass) {
        return FController.ResolveObject(aName, aClass);
    }

    public void RenderScene() {
        FController.RenderScene();
    }

    public Integer GridDistance;

}
