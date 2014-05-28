package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;

public class NGGameEngineMemoryCellValue extends NGUniplayObject {

    protected Integer FInteger;

    public NGGameEngineMemoryCellValue(Integer aInteger) {
        super();
        FInteger = aInteger;
    }

    public void setInteger(int aInteger){
        FInteger = aInteger;
    }

    public Integer getInteger() {
        return FInteger;
    }

    public void clear() {
        FInteger = 0;
    }

    public void Inc() {
        FInteger = FInteger + 1;
    }

}
