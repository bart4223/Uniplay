package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniwork.Visuals.NGDisplayController;

public class NG2DRenderEngine extends NGRenderEngine {

    @Override
    protected void DoBeforeRender() {
        super.DoBeforeRender();
        for (NGDisplayController dc : FControllers) {
            NGGameEngineMemoryAddress address = Cell.getAddress();
            dc.setPosition(address.getOffset() * dc.getWidth(), address.getBase() * dc.getHeight());
            dc.setProperty(dc, ValuePropname, Cell.getValueAsInteger());
        }
    }

    public NG2DRenderEngine(String aName) {
        super(aName);
    }

}
