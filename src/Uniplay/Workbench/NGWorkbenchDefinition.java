package Uniplay.Workbench;

import Uniplay.Kernel.NGGameEngineModuleDefinition;

public class NGWorkbenchDefinition extends NGGameEngineModuleDefinition {

    protected NGLevelDesignerManagerDefinition LevelDesignerManager;

    public NGWorkbenchDefinition() {
        super();
    }

    public void setLevelDesignerManager(NGLevelDesignerManagerDefinition value) { LevelDesignerManager = value;}
    public NGLevelDesignerManagerDefinition getLevelDesignerManager() { return LevelDesignerManager; }


}
