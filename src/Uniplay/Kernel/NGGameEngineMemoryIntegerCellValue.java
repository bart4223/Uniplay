package Uniplay.Kernel;

public class NGGameEngineMemoryIntegerCellValue extends NGGameEngineMemoryObjectCellValue {

    public NGGameEngineMemoryIntegerCellValue() {
        this(0);
    }

    public NGGameEngineMemoryIntegerCellValue(Integer aInteger) {
        super(aInteger);
    }

    @Override
    public void setInteger(Integer aInteger) {
        setObject(aInteger);
    }

    @Override
    public Integer getInteger() {
        return (Integer)getObject();
    }

    @Override
    public void clear() {
        setInteger(0);
    }

    @Override
    public void inc() {
        setInteger(getInteger() + 1);
    }

}
