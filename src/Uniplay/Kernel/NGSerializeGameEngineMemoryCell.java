package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObjectDefinition;

public class NGSerializeGameEngineMemoryCell extends NGUniplayObjectDefinition {

    protected Integer Value = 0;
    protected Integer Base = 0;
    protected Integer Offset = 0;

    public NGSerializeGameEngineMemoryCell() {
        super();
    }

    public void setValue(Integer value) {
        Value = value;
    }

    public Integer getValue() {
        return Value;
    }

    public void setBase(Integer value) {
        Base = value;
    }

    public Integer getBase() {
        return Base;
    }

    public void setOffset(Integer value) {
        Offset = value;
    }

    public Integer getOffset() {
        return Offset;
    }

}
