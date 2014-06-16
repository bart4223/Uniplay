package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Visuals.NGDisplayView;

public class NGRenderEngineItem extends NGUniplayObject {

    protected int FLayerIndex;
    protected NGDisplayView FView;
    protected NG2DRenderEngine FRenderEngine;

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
