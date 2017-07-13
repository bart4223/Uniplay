package Uniplay.Workbench;

import Uniplay.Base.NGUniplayComponentDefinition;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGLevelDesignerDefinition extends NGUniplayComponentDefinition {

    protected CopyOnWriteArrayList<NGSerializePropertyItem> Props;
    protected String Levelcaption;
    protected String Levelname;

    public NGLevelDesignerDefinition() {
        super();
    }

    public void setProps(CopyOnWriteArrayList<NGSerializePropertyItem> value) { Props = value;}
    public CopyOnWriteArrayList<NGSerializePropertyItem> getProps() { return Props; }
    public void setLevelcaption(String value) { Levelcaption = value;}
    public String getLevelcaption() { return Levelcaption;}
    public void setLevelname(String value) { Levelname = value;}
    public String getLevelname() { return Levelname;}

}
