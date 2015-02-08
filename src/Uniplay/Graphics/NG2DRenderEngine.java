package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniwork.Base.NGPropertyItem;
import Uniwork.Visuals.NGDisplayController;

import java.util.ArrayList;

public class NG2DRenderEngine extends NGCustomRenderEngine {

    @Override
    protected void prepareDisplayController(NGDisplayController aDisplayController) {
        super.prepareDisplayController(aDisplayController);
        NGGameEngineMemoryAddress address = Cell.getAddress();
        aDisplayController.setPosition(address.getOffset() * aDisplayController.getWidth(), address.getBase() * aDisplayController.getHeight());
        if (Cell.getValueAsObject() instanceof NGRenderInformation) {
            NGRenderInformation ri = (NGRenderInformation)Cell.getValueAsObject();
            ArrayList<NGPropertyItem> props = ri.getDisplayControllerProps(getName(), aDisplayController);
            for (NGPropertyItem prop : props) {
                aDisplayController.setProperty(aDisplayController, prop.getName(), prop.getValue());
            }
            aDisplayController.setProperty(aDisplayController, String.format("%s.%s", ValueLayername, ValuePropname), ri.getValueForDisplayController(getName(), aDisplayController));
            props = ri.getDisplayControllerLayerProps(getName(), aDisplayController);
            for (NGPropertyItem prop : props) {
                aDisplayController.setProperty(aDisplayController, String.format("%s.%s", prop.getName(), ValuePropname), prop.getValue());
            }
        }
    }

    public NG2DRenderEngine(String aName) {
        super(aName);
    }

    public NGGameEngineMemoryCell Cell;

}
