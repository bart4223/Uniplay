package Uniplay.Kernel;

import Uniwork.Graphics.NGPoint2D;
import Uniwork.Visuals.NGGeometryObject2DDisplayManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.Random;

public class NGSplashGeometryObjects extends NGSplashItem {

    protected NGGeometryObject2DDisplayManager FDisplayController;
    protected Integer FCount;
    protected Random FGenerator;

    @Override
    protected void DoRun() {
        super.DoRun();
        NGPoint2D point = new NGPoint2D(FGenerator.nextInt(25), FGenerator.nextInt(20));
        FDisplayController.GeometryObject = point;
        FCount++;
    }

    @Override
    protected void InitRun() {
        super.InitRun();
        FCount = 0;
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        Canvas canvas = (Canvas) FManager.getControllerObject("Layer1", Canvas.class);
        FDisplayController = new NGGeometryObject2DDisplayManager(canvas, "DCG1");
        FDisplayController.GeometryObjectColor = Color.valueOf(GeometryObjectColor);
        FDisplayController.setPixelSize(32);
        FDisplayController.Initialize();
        registerDisplayController(FDisplayController);
    }


    public NGSplashGeometryObjects(NGSplashManager aManager, String aName) {
        super(aManager, aName);
        FGenerator = new Random();
        FCount = 0;
    }

    @Override
    public Boolean getFinished() {
        return FCount > 100;
    }

    public String GeometryObjectColor;

}
