package Uniplay.Workbench;

import Uniplay.Base.NGUniplayComponentDefinition;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.ArrayList;

public class NGLevelDesignerDefinition extends NGUniplayComponentDefinition {

    protected ArrayList<NGSerializePropertyItem> Props;
    protected String Levelcaption;
    protected String Levelname;

    public NGLevelDesignerDefinition() {
        super();
    }

    public void setProps(ArrayList<NGSerializePropertyItem> value) { Props = value;}
    public ArrayList<NGSerializePropertyItem> getProps() { return Props; }
    public void setLevelcaption(String value) { Levelcaption = value;}
    public String getLevelcaption() { return Levelcaption;}
    public void setLevelname(String value) { Levelname = value;}
    public String getLevelname() { return Levelname;}

}
