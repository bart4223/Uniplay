package Uniplay.Workbench;

import Uniplay.Base.NGUniplayComponentDefinition;

import java.util.ArrayList;

public class NGLevelDesignerManagerDefinition extends NGUniplayComponentDefinition {

    protected ArrayList<NGLevelDesignerDefinition> LevelDesigners;

    public NGLevelDesignerManagerDefinition() {
        super();
    }

    public void setLevelDesigners(ArrayList<NGLevelDesignerDefinition> value) { LevelDesigners = value;}
    public ArrayList<NGLevelDesignerDefinition> getLevelDesigners() { return LevelDesigners; }

}
