package Uniplay.Graphics;

import Uniwork.Visuals.NGDisplayView;

public class NG2DRenderEngineItem extends NGCustomRenderEngineItem {

    protected NGDisplayView FView;

    public NG2DRenderEngineItem(NGCustomRenderEngine aRenderEngine, int aLayerIndex, double aWidth, double aHeight) {
        super(aRenderEngine, aLayerIndex);
        FView = new NGDisplayView(aWidth, aHeight);
        setDisplayView(FView);
    }

    public void setViewPosition(double aX, double aY) {
        FView.setPosition(aX, aY);
    }

}
