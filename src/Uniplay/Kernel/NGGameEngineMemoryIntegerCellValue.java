package Uniplay.Kernel;

public class NGGameEngineMemoryIntegerCellValue extends NGGameEngineMemoryCustomCellValue {

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

    public void clear() {
        setInteger(0);
    }

    public void Inc() {
        setInteger(getInteger() + 1);
    }

}
