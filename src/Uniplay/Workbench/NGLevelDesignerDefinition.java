package Uniplay.Workbench;

import Uniplay.Base.NGUniplayComponentDefinition;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.ArrayList;

public class NGLevelDesignerDefinition extends NGUniplayComponentDefinition {

    protected ArrayList<NGSerializePropertyItem> Props;

    public NGLevelDesignerDefinition() {
        super();
    }

    public void setProps(ArrayList<NGSerializePropertyItem> value) { Props = value;}
    public ArrayList<NGSerializePropertyItem> getProps() { return Props; }

}
