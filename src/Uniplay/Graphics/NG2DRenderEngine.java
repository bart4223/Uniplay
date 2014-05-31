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
            dc.setPosition(address.getOffset() * dc.BaseWidth, address.getBase() * dc.BaseHeight);
            dc.setProperty(dc, FValuePropName, Cell.getValue());
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
