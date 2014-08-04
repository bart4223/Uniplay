package Uniplay.Misc;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniwork.Visuals.NGDisplayController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import static java.lang.Thread.sleep;

public class NGSplashManager extends NGUniplayComponent {

    protected Stage FStage;
    protected ArrayList<NGSplashItem> FItems;
    protected NGSplashStageController FController;

    protected void CreateStage(){
        FStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("NGSplashStage.fxml"));
        try {
            lXMLLoader.load();
            FController = lXMLLoader.getController();
            FController.Manager = this;
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
                        int index = aIndex + 1;
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
                            aSplashManager.closeStage();
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

    public NGSplashManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FItems = new ArrayList<NGSplashItem>();
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
        FController.RenderScene();
        showStage();
        playSound();
        DoRun();
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

    protected void playSound() {
        // ToDo
        //MediaPlayer mp = new MediaPlayer(new Media("file:///Users/Nils/IdeaProjects/Boulderdash/resources/sound/source.mp3"));
        MediaPlayer mp = new MediaPlayer(new Media("file:///Users/Nils/IdeaProjects/Boulderdash/resources/sound/source.mp3"));
        mp.play();
    }

}