package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGPropertyList;

public class NGGameEngineMemoryCell extends NGUniplayObject {

    protected NGGameEngineMemoryAddress FAddress;
    protected NGGameEngineMemoryCellValue FValue;
    protected NGPropertyList FProps;

    public NGGameEngineMemoryCell(int aPage, int aBase, int aOffset) {
        super();
        FValue = new NGGameEngineMemoryCellValue(0);
        FAddress = new NGGameEngineMemoryAddress(aPage, aBase, aOffset);
        FProps = new NGPropertyList();
    }

    public NGGameEngineMemoryAddress getAddress() {
        return FAddress;
    }

    public void setValue(Integer aValue) {
        FValue.setInteger(aValue);
    }

    public Integer getValue() {
        return FValue.getInteger();
    }

    public void setPropValue(String aName, Object aValue) {
        FProps.set(aName, aValue);
    }

    public Object getPropValue(String aName) {
        return FProps.get(aName);
    }

    public void incValue() {
        FValue.Inc();
    }

    public void clear() {
        FValue.clear();
        FProps.clear();
    }

}
