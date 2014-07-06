package Uniplay.Base;

import Uniwork.Base.NGObject;

public class NGUniplayRegisteredObjectItem extends NGObject {

    protected String FName;
    protected Object FObject;

    public NGUniplayRegisteredObjectItem(String aName, Object aObject) {
        super();
        FObject = aObject;
        FName = aName;
    }

    public String getName() {
        return FName;
    }

    public Object getObject() {
        return FObject;
    }

}
