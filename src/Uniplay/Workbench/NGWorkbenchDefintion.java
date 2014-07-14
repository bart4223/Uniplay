package Uniplay.Workbench;

import Uniplay.Kernel.NGGameEngineModuleDefinition;

public class NGWorkbenchDefintion extends NGGameEngineModuleDefinition {

    protected NGLevelDesignerManagerDefinition LevelDesignerManager;

    public NGWorkbenchDefintion() {
        super();
    }

    public void setLevelDesignerManager(NGLevelDesignerManagerDefinition value) { LevelDesignerManager = value;}
    public NGLevelDesignerManagerDefinition getLevelDesignerManager() { return LevelDesignerManager; }


}
