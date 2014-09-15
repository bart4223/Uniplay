package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;

public class NGGameEngineMemoryCellValueItem extends NGUniplayObject {

    protected NGGameEngineMemoryAddress FAddress;
    protected NGGameEngineMemoryObjectCellValue FValue;

    public NGGameEngineMemoryCellValueItem(NGGameEngineMemoryAddress aAddress, NGGameEngineMemoryObjectCellValue aValue) {
        super();
        FAddress = aAddress;
        FValue = aValue;
    }

    public NGGameEngineMemoryAddress getAddress() {
        return FAddress;
    }

    public NGGameEngineMemoryObjectCellValue getValue() {
        return FValue;
    }

}
