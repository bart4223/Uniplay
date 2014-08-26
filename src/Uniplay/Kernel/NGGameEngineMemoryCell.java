package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGPropertyList;

public class NGGameEngineMemoryCell extends NGUniplayObject {

    protected NGGameEngineMemoryAddress FAddress;
    protected NGGameEngineMemoryCustomCellValue FValue;
    protected NGPropertyList FProps;

    public NGGameEngineMemoryCell(Integer aPage, Integer aBase, Integer aOffset, Class aCellValueClass) {
        super();
        try {
            FValue = (NGGameEngineMemoryCustomCellValue)aCellValueClass.getConstructor().newInstance();
        } catch (Exception e) {
            FValue = null;
        }
        FAddress = new NGGameEngineMemoryAddress(aPage, aBase, aOffset);
        FProps = new NGPropertyList();
    }

    public NGGameEngineMemoryAddress getAddress() {
        return FAddress;
    }

    public NGGameEngineMemoryCustomCellValue getValue() {
        return FValue;
    }

    public void setValueAsInteger(Integer aValue) {
        FValue.setInteger(aValue);
    }

    public Integer getValueAsInteger() {
        return FValue.getInteger();
    }

    public void setPropValue(String aName, Object aValue) {
        FProps.set(aName, aValue);
    }

    public Object getPropValue(String aName) {
        return FProps.get(aName);
    }

    public void incValue() {
        FValue.inc();
    }

    public void clear() {
        FValue.clear();
        FProps.clear();
    }

}
