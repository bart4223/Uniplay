package Uniplay.Workbench;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

public class NGLevelDesignerManager extends NGUniplayComponent {

    protected NGLevelDesignerManagerDefinition FDefinition;

    public NGLevelDesignerManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FDefinition = null;
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

    public void setDefinitions(NGLevelDesignerManagerDefinition aDefinition) {
        FDefinition = aDefinition;
    }

    public NGLevelDesignerManagerDefinition getDefinition() {
        return FDefinition;
    }

    public NGLevelDesignerDefinition getLevelDesignerDefinition(String aName) {
        for (NGLevelDesignerDefinition def : FDefinition.getLevelDesigners()) {
            if (def.getName().equals(aName)) {
                return def;
            }
        }
        return null;
    }


}
