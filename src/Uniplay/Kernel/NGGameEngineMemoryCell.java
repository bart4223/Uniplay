package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGPropertyList;

public class NGGameEngineMemoryCell extends NGUniplayObject {

    protected NGGameEngineMemoryAddress FAddress;
    protected NGGameEngineMemoryObjectCellValue FValue;
    protected NGPropertyList FProps;

    public NGGameEngineMemoryCell(Integer aPage, Integer aBase, Integer aOffset, Class aCellValueClass) {
        super();
        try {
            FValue = (NGGameEngineMemoryObjectCellValue)aCellValueClass.getConstructor().newInstance();
        } catch (Exception e) {
            FValue = null;
        }
        FAddress = new NGGameEngineMemoryAddress(aPage, aBase, aOffset);
        FProps = new NGPropertyList();
    }

    public NGGameEngineMemoryAddress getAddress() {
        return FAddress;
    }

    public NGGameEngineMemoryObjectCellValue getValue() {
        return FValue;
    }

    public void setValueAsObject(Object aValue) {
        FValue.setObject(aValue);
    }

    public void setValueAsInteger(Integer aValue) {
        FValue.setInteger(aValue);
    }

    public Object getValueAsObject() {
        return FValue.getObject();
    }

    public Integer getValueAsInteger() {
        return FValue.getInteger();
    }

    public void setPropValue(String aName, Object aValue) {
        FProps.set(aName, aValue);
    }

    public Object getPropValue(String aName) {
        Object prop = FProps.get(aName);
        if (prop == null) {
            return FValue.getProperty(FValue, aName);
        }
        return prop;
    }

    public void incValue() {
        FValue.inc();
    }

    public void clear() {
        FValue.clear();
        FProps.clear();
    }

}
