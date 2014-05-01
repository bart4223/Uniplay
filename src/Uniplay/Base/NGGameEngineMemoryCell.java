package Uniplay.Base;

import Uniwork.Base.NGObject;
import Uniwork.Base.NGPropertyList;

public class NGGameEngineMemoryCell extends NGObject {

    protected int FAddress;
    protected NGPropertyList FProps;

    public NGGameEngineMemoryCell(int aAddress) {
        super();
        FAddress = aAddress;
        FProps = new NGPropertyList();
    }

    public int getAddress() {
        return FAddress;
    }

    public void setPropValue(String aName, Object aValue) {
        FProps.set(aName, aValue);
    }

    public Object getPropValue(String aName) {
        return FProps.get(aName);
    }

}
