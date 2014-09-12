package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniwork.Visuals.NGDisplayManager;

public abstract class NGCustomRenderEngine extends NGDisplayManager {

    public NGCustomRenderEngine(String aName) {
        super(null, aName);
        ValueLayername = "";
        ValuePropname = "";
    }

    public NGGameEngineMemoryCell Cell;
    public String ValueLayername;
    public String ValuePropname;

}
