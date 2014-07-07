package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGPropertyList;

public class NGSplashItem extends NGUniplayObject {

    protected String FName;
    protected NGPropertyList FProps;

    public NGSplashItem(String aName) {
        super();
        FName = aName;
        FProps = new NGPropertyList();
    }

    public String getName() {
        return FName;
    }

    public void Run() {

    }

}
