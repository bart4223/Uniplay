package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;

public class NGGameEngineMemoryCellValueItem extends NGUniplayObject {

    protected NGGameEngineMemoryAddress FAddress;
    protected NGGameEngineMemoryCustomCellValue FValue;

    public NGGameEngineMemoryCellValueItem(NGGameEngineMemoryAddress aAddress, NGGameEngineMemoryCustomCellValue aValue) {
        super();
        FAddress = aAddress;
        FValue = aValue;
    }

    public NGGameEngineMemoryAddress getAddress() {
        return FAddress;
    }

    public NGGameEngineMemoryCustomCellValue getValue() {
        return FValue;
    }

}
