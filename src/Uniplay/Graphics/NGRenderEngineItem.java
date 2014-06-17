package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Visuals.NGDisplayView;

public class NGRenderEngineItem extends NGUniplayObject {

    protected int FLayerIndex;
    protected NGDisplayView FView;
    protected NG2DRenderEngine FRenderEngine;

    @Override
    public void setProperty(Object aObject, String aName, Object aValue) {
        int index = aName.indexOf(".");
        if (index >= 0) {
            String name = aName.substring(0, index);
            if (name.equals("RenderEngine")) {
                FRenderEngine.setProperty(FRenderEngine, aName.substring(index + 1, aName.length()), aValue);
            }
            else
                super.setProperty(aObject, aName, aValue);
        }
        else
            super.setProperty(aObject, aName, aValue);
    }

    public NGRenderEngineItem(NG2DRenderEngine aRenderEngine, int aLayerIndex, double aWidth, double aHeight) {
        super();
        FView = new NGDisplayView(aWidth, aHeight);
        FRenderEngine = aRenderEngine;
        FRenderEngine.setView(FView);
        FLayerIndex = aLayerIndex;
    }

    public int getLayerIndex() {
        return FLayerIndex;
    }

    public NG2DRenderEngine getRenderEngine() {
        return FRenderEngine;
    }

}
