package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;

public abstract class NGGameEngineMemoryCustomCellValue extends NGUniplayObject implements NGGameEngineMemoryCellValue {

    protected Object FObject;

    public NGGameEngineMemoryCustomCellValue() {
        this(null);
    }

    public NGGameEngineMemoryCustomCellValue(Object aObject) {
        super();
        FObject = aObject;
    }

    public void setObject(Object aObject) {
        FObject = aObject;
    }

    public Object getObject() {
        return FObject;
    }

    public Object getPropValue(String aName) {
        return null;
    }

}
