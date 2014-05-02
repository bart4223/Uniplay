package Uniplay.Base;

import Uniwork.Base.NGObject;
import Uniwork.Base.NGPropertyList;

public class NGGameEngineMemoryCell extends NGObject {

    protected int FAddress;
    protected Object FValue;
    protected NGPropertyList FProps;

    public NGGameEngineMemoryCell(int aAddress) {
        super();
        FAddress = aAddress;
        FProps = new NGPropertyList();
        FValue = null;
    }

    public int getAddress() {
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
