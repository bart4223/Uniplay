package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGPropertyList;
import Uniwork.Base.NGQualityOfService;

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

    @Override
    public Boolean setProperty(Object aObject, String aName, Object aValue) {
        Boolean res = super.setProperty(aObject, aName, aValue);
        if (!res) {
           res = FProps.set(aName, aValue) != null;
        }
        return res;
    }

    @Override
    public Object getProperty(Object aObject, String aName) {
        Object res =  super.getProperty(aObject, aName);
        if (res == null) {
            res = FProps.get(aName);
            if (res == null) {
                res = FValue.getProperty(FValue, aName);
            }
        }
        return res;
    }

    public void incValue() {
        FValue.inc();
    }

    public void clear() {
        FValue.clear();
        FProps.clear();
    }

}
