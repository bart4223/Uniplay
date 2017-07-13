package Uniplay.Workbench;

import Uniplay.Base.NGUniplayComponentDefinition;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGLevelDesignerManagerDefinition extends NGUniplayComponentDefinition {

    protected CopyOnWriteArrayList<NGLevelDesignerDefinition> LevelDesigners;

    public NGLevelDesignerManagerDefinition() {
        super();
    }

    public void setLevelDesigners(CopyOnWriteArrayList<NGLevelDesignerDefinition> value) { LevelDesigners = value;}
    public CopyOnWriteArrayList<NGLevelDesignerDefinition> getLevelDesigners() { return LevelDesigners; }

}
