package Uniplay.Storage;

import Uniplay.Base.NGUniplayObjectDefinition;
import Uniplay.Kernel.NGSerializeGameEngineMemoryCell;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.ArrayList;

public class NGSerialize2DGameFieldLayer extends NGUniplayObjectDefinition {

    protected ArrayList<NGSerializeGameEngineMemoryCell> Cells;
    protected String Name;
    protected String Cellsclassname;
    protected ArrayList<NGSerializePropertyItem> Props;

    public NGSerialize2DGameFieldLayer() {
        super();
    }

    public void setCells(ArrayList<NGSerializeGameEngineMemoryCell> Value) {
        Cells = Value;
    }

    public ArrayList<NGSerializeGameEngineMemoryCell> getCells() {
        return Cells;
    }

    public void setName(String value) {
        Name = value;
    }

    public String getName() {
        return Name;
    }

    public void setCellsclassname(String value) {
        Cellsclassname = value;
    }

    public String getCellsclassname() {
        return Cellsclassname;
    }

    public void setProps(ArrayList<NGSerializePropertyItem> Value) {
        Props = Value;
    }

    public ArrayList<NGSerializePropertyItem> getProps() {
        return Props;
    }

}
