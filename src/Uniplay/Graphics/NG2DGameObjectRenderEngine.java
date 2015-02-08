package Uniplay.Graphics;

import Uniplay.Storage.NGCustomGameObject;
import Uniwork.Visuals.NGDisplayController;

public class NG2DGameObjectRenderEngine extends NGCustomRenderEngine {

    @Override
    protected void prepareDisplayController(NGDisplayController aDisplayController) {
        super.prepareDisplayController(aDisplayController);
        aDisplayController.setProperty(aDisplayController, String.format("%s", ValuePropname), GameObject);
    }

    public NG2DGameObjectRenderEngine(String aName) {
        super(aName);
    }

    public NGCustomGameObject GameObject;

}
