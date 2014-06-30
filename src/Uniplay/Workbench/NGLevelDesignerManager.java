package Uniplay.Workbench;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

public class NGLevelDesignerManager extends NGUniplayComponent {

    public NGLevelDesignerManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
    }

    public NGLevelDesigner addLevelDesigner(String aName) {
        NGLevelDesigner designer = new NGLevelDesigner(FOwner, aName);
        addSubComponent(designer);
        designer.Initialize();
        designer.showStage();
        return designer;
    }

}
