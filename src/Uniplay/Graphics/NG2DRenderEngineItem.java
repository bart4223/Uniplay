package Uniplay.Graphics;

import Uniwork.Visuals.NGDisplayController;
import Uniwork.Visuals.NGDisplayView;

public class NG2DRenderEngineItem extends NGCustomRenderEngineItem {

    protected NGDisplayView FView;

    public NG2DRenderEngineItem(NGRenderEngine aRenderEngine, int aLayerIndex, double aWidth, double aHeight) {
        super(aRenderEngine, aLayerIndex);
        FView = new NGDisplayView(aWidth, aHeight);
        getRenderEngine().setView(FView);
    }

    public void setViewPosition(double aX, double aY) {
        NGDisplayController dc = FRenderEngine.getController("DEFAULT");
        // ToDo
        //FView.setPosition(aX * dc.getWidth(), aY * dc.getHeight());
        FView.setPosition(aX, aY);
    }

}
