package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniwork.Visuals.NGDisplayController;
import Uniwork.Visuals.NGDisplayManager;

public class NGRenderEngine extends NGDisplayManager {

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

    public NGRenderEngine(String aName) {
        super(null, aName);
        FValuePropName = "";
    }

    public NGGameEngineMemoryCell Cell;

    public void setValuePropName(String aName) {
        FValuePropName = aName;
    }

    public String getValuePropName() {
        return FValuePropName;
    }

}
