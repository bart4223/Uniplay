package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObject;

public class NGDisplayControllerLayerProp extends NGUniplayObject {

    protected String FName;
    protected Integer FValue;

    public NGDisplayControllerLayerProp(String aName, Integer aValue) {
        super();
        FName = aName;
        FValue = aValue;
    }

    public String getName() {
        return FName;
    }

    public Integer getValue() {
        return FValue;
    }

}
