package Uniplay.Workbench;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGPropertyList;

public class NGLevelDesigner extends NGUniplayComponent {

    protected NGPropertyList FProps;
    protected String FLevelName;
    protected String FLevelCaption;

    public NGLevelDesigner(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FProps = new NGPropertyList();
        FLevelName = "";
        FLevelCaption = "";
    }

    public NGPropertyList getProps() {
        return FProps;
    }

    public void setLevelName(String aLevelName) {
        FLevelName = aLevelName;
    }

    public String getLevelName() {
        return FLevelName;
    }

    public void setLevelCaption(String aLevelCaption) {
        FLevelName = aLevelCaption;
    }

    public String getLevelCaption() {
        return FLevelCaption;
    }

}
