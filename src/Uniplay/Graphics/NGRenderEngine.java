package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniwork.Visuals.NGDisplayManager;

public class NGRenderEngine extends NGDisplayManager {

    public NGRenderEngine(String aName) {
        super(null, aName);
        ValueLayername = "";
        ValuePropname = "";
    }

    public NGGameEngineMemoryCell Cell;
    public String ValueLayername;
    public String ValuePropname;

}
