package Uniplay.Workbench;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

public class NGLevelDesignerManager extends NGUniplayComponent {

    public NGLevelDesignerManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
    }

    public NG2DLevelDesigner addLevelDesigner(String aName) {
        NG2DLevelDesigner designer = new NG2DLevelDesigner(this, aName);
        addSubComponent(designer);
        designer.Initialize();
        return designer;
    }

    public NG2DLevelDesigner getLevelDesigner(String aName) {
        return (NG2DLevelDesigner)getSubComponent(aName);
    }

}
