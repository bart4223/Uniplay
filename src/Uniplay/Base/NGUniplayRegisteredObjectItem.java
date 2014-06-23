package Uniplay.Base;

import Uniwork.Base.NGObject;

public class NGUniplayRegisteredObjectItem extends NGObject {

    protected String FName;
    protected NGObject FObject;

    public NGUniplayRegisteredObjectItem(String aName, NGObject aObject) {
        super();
        FObject = aObject;
        FName = aName;
    }

    public String getName() {
        return FName;
    }

    public NGObject getObject() {
        return FObject;
    }

}
