package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;

public class NGGameEngineMemoryCellValueItem extends NGUniplayObject {

    protected NGGameEngineMemoryAddress FAddress;
    protected NGGameEngineMemoryCellValue FValue;

    public NGGameEngineMemoryCellValueItem(NGGameEngineMemoryAddress aAddress, NGGameEngineMemoryCellValue aValue) {
        super();
        FAddress = aAddress;
        FValue = aValue;
    }

    public NGGameEngineMemoryAddress getAddress() {
        return FAddress;
    }

    public NGGameEngineMemoryCellValue getValue() {
        return FValue;
    }

}
