package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObjectDefinition;

public class NGSerializeGameEngineMemoryCell extends NGUniplayObjectDefinition {

    protected Integer Value;

    public NGSerializeGameEngineMemoryCell() {
        super();
    }

    public void setValue(Integer value) {
        Value = value;
    }

    public Integer getValue() {
        return Value;
    }

}
