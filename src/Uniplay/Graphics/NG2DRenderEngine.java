package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniwork.Visuals.NGDisplayController;

public class NG2DRenderEngine extends NGCustomRenderEngine {

    protected void prepareDisplayController(NGDisplayController aDisplayController) {
        NGGameEngineMemoryAddress address = Cell.getAddress();
        aDisplayController.setPosition(address.getOffset() * aDisplayController.getWidth(), address.getBase() * aDisplayController.getHeight());
        aDisplayController.setProperty(aDisplayController, String.format("%s.%s", ValueLayername, ValuePropname), Cell.getValueAsInteger());
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

    public NG2DRenderEngine(String aName) {
        super(aName);
    }

}
