package Uniplay.Graphics;

import Uniwork.Visuals.NGDisplayController;
import Uniwork.Visuals.NGDisplayManager;

public abstract class NGCustomRenderEngine extends NGDisplayManager {

    protected void prepareDisplayController(NGDisplayController aDisplayController) {

    }

    @Override
    protected void DoBeforeRender() {
        super.DoBeforeRender();
        if (getCurrentController() == null) {
            for (NGDisplayController dc : FControllers) {
                prepareDisplayController(dc);
            }
        }
        else {
            prepareDisplayController(getCurrentController());
        }
    }

    public NGCustomRenderEngine(String aName) {
        super(null, aName);
        ValueLayername = "";
        ValuePropname = "";
    }

    public String ValueLayername;
    public String ValuePropname;

}
