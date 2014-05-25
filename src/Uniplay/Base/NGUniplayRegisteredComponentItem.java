package Uniplay.Base;

import Uniwork.Base.NGObject;

public class NGUniplayRegisteredComponentItem extends NGObject {

    protected String FName;
    protected NGUniplayComponent FComponent;

    public NGUniplayRegisteredComponentItem(String aName, NGUniplayComponent aComponent) {
        super();
        FComponent = aComponent;
        FName = aName;
    }

    public String getName() {
        return FName;
    }

    public NGUniplayComponent getComponent() {
        return FComponent;
    }

}
