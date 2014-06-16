package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniwork.Visuals.NGDisplayController;

public class NG2DRenderEngine extends NGRenderEngine {

    protected String FValuePropName;

    @Override
    protected void DoBeforeRender() {
        super.DoBeforeRender();
        for (NGDisplayController dc : FControllers) {
            NGGameEngineMemoryAddress address = Cell.getAddress();
            dc.setPosition(address.getOffset() * dc.getWidth(), address.getBase() * dc.getHeight());
            dc.setProperty(dc, FValuePropName, Cell.getValue());
            // ToDo more Props
        }
    }

    public NG2DRenderEngine(String aName) {
        super(aName);
        FValuePropName = "";
    }

    public void setValuePropName(String aName) {
        FValuePropName = aName;
    }

    public String getValuePropName() {
        return FValuePropName;
    }

}
