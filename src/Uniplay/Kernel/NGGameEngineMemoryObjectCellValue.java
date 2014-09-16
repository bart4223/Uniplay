package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGQualityOfService;

public class NGGameEngineMemoryObjectCellValue extends NGUniplayObject implements NGGameEngineMemoryCellValue {

    protected Object FObject;

    public NGGameEngineMemoryObjectCellValue() {
        this(null);
    }

    public NGGameEngineMemoryObjectCellValue(Object aObject) {
        super();
        FObject = aObject;
    }

    public void setObject(Object aObject) {
        FObject = aObject;
    }

    public Object getObject() {
        return FObject;
    }

    @Override
    public Boolean setProperty(Object aObject, String aName, Object aValue) {
        Boolean res = super.setProperty(aObject, aName, aValue);
        if (!res && FObject instanceof NGQualityOfService) {
            NGQualityOfService QOS = (NGQualityOfService)FObject;
            res = QOS.setProperty(FObject, aName, aValue);
        }
        return res;
    }

    @Override
    public Object getProperty(Object aObject, String aName) {
        Object result =  super.getProperty(aObject, aName);
        if (result == null && FObject instanceof NGQualityOfService) {
            NGQualityOfService QOS = (NGQualityOfService)FObject;
            result = QOS.getProperty(FObject, aName);
        }
        return result;
    }

    @Override
    public Integer getInteger() {
        return 0;
    }

    @Override
    public void setInteger(Integer aInteger) {

    }

    @Override
    public void inc() {

    }

    @Override
    public void clear() {

    }

}
