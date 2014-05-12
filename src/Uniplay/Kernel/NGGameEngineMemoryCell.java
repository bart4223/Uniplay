package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGPropertyList;

public class NGGameEngineMemoryCell extends NGUniplayObject {

    protected NGGameEngineMemoryAddress FAddress;
    protected Object FValue;
    protected NGPropertyList FProps;

    public NGGameEngineMemoryCell(int aPage, int aBase, int aOffset) {
        super();
        FAddress = new NGGameEngineMemoryAddress(aPage, aBase, aOffset);
        FProps = new NGPropertyList();
        FValue = null;
    }

    public NGGameEngineMemoryAddress getAddress() {
        return FAddress;
    }

    public void setValue(Object aValue) {
        FValue = aValue;
    }

    public Object getValue() {
        return FValue;
    }

    public void setPropValue(String aName, Object aValue) {
        FProps.set(aName, aValue);
    }

    public Object getPropValue(String aName) {
        return FProps.get(aName);
    }

    public void clear() {
        FValue = null;
        FProps.clear();
    }

}
