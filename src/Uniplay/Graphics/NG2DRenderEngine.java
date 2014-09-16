package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.NGGameEngineConstants;
import Uniwork.Visuals.NGDisplayController;

import java.util.ArrayList;

public class NG2DRenderEngine extends NGCustomRenderEngine {

    protected void prepareDisplayController(NGDisplayController aDisplayController) {
        NGGameEngineMemoryAddress address = Cell.getAddress();
        aDisplayController.setPosition(address.getOffset() * aDisplayController.getWidth(), address.getBase() * aDisplayController.getHeight());
        aDisplayController.setProperty(aDisplayController, String.format("%s.%s", ValueLayername, ValuePropname), Cell.getValueAsInteger());
        Object obj = Cell.getProperty(Cell, NGGameEngineConstants.PROP_GRAPHIC_DISPLAYCONTROLLER_LAYER_PROPS);
        if (obj instanceof ArrayList<?>) {
            ArrayList<NGDisplayControllerLayerProp> props = (ArrayList<NGDisplayControllerLayerProp>)obj;
            for (NGDisplayControllerLayerProp prop : props) {
                aDisplayController.setProperty(aDisplayController, String.format("%s.%s", prop.getName(), ValuePropname), prop.getValue());
            }
        }
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
