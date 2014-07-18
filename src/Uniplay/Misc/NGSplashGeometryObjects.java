package Uniplay.Misc;

import Uniwork.Base.NGObjectDeserializer;
import Uniwork.Base.NGObjectXMLDeserializerFile;
import Uniwork.Base.NGSerializePropertyItem;
import Uniwork.Graphics.*;
import Uniwork.Visuals.NGGeometryObject2DDisplayManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class NGSplashGeometryObjects extends NGSplashItem {

    protected class GeometryObjectItem {

        protected NGGeometryObject2D FGO;

        public GeometryObjectItem(NGGeometryObject2D aGO) {
            FGO = aGO;
        }

        public NGGeometryObject2D getGO() {
            return FGO;
        }

        public Color ObjectColor;

    }

    protected NGGeometryObject2DDisplayManager FDisplayController;
    protected ArrayList<GeometryObjectItem> FGeometryObjects;
    protected Random FGenerator;
    protected GeometryObjectItem FCurrentItem;

    @Override
    protected void DoRun() {
        super.DoRun();
        if (FGeometryObjects != null) {
            Integer index = FGenerator.nextInt(FGeometryObjects.size());
            FCurrentItem = FGeometryObjects.get(index);
            FDisplayController.GeometryObject = FCurrentItem.getGO();
            FDisplayController.GeometryObjectColor = FCurrentItem.ObjectColor;
        }
    }

    @Override
    protected void AfterRun() {
        super.AfterRun();
        if (FCurrentItem != null)
            FGeometryObjects.remove(FCurrentItem);
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        Canvas canvas = (Canvas) FManager.getControllerObject(ControllerObjectName, Canvas.class);
        FDisplayController = new NGGeometryObject2DDisplayManager(canvas, String.format("DMGO.%s", ControllerObjectName));
        FDisplayController.GeometryObjectColor = Color.valueOf(GeometryObjectColor);
        FDisplayController.setPixelSize(16);
        FDisplayController.Initialize();
        registerDisplayController(FDisplayController);
        NGObjectDeserializer Deserializer = new NGObjectXMLDeserializerFile(this, Filename);
        Deserializer.setLogManager(getLogManager());
        Deserializer.deserializeObject();
    }

    @Override
    protected void DoAssignFrom(Object aObject) {
        if (aObject instanceof NGSerializeGeometryObjectList) {
            NGSerializeGeometryObjectList sgol = (NGSerializeGeometryObjectList)aObject;
            FGeometryObjects.clear();
            for (NGSerializeGeometryObjectItem sgoi : sgol.getSGOS()) {
                if (sgoi.getGO() instanceof NGGeometryObject2D) {
                    NGGeometryObject2D go = (NGGeometryObject2D)sgoi.getGO();
                    GeometryObjectItem item = new GeometryObjectItem(go);
                    for (NGSerializePropertyItem prop : sgoi.getProps()) {
                        if (prop.getName().equals("COLOR"))
                            item.ObjectColor = Color.valueOf((String)prop.getValue());
                    }
                    FGeometryObjects.add(item);
                }
            }
        }
    }

    public NGSplashGeometryObjects(NGSplashManager aManager, String aName) {
        super(aManager, aName);
        FGenerator = new Random();
        FGeometryObjects = new ArrayList<GeometryObjectItem>();
        FCurrentItem = null;
    }

    @Override
    public Boolean getFinished() {
        return FGeometryObjects.size() == 0;
    }

    public String GeometryObjectColor;

}
