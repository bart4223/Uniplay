package Uniplay.Storage;

import Uniplay.Base.NGUniplayObjectDefinition;
import Uniplay.Kernel.NGSerializeGameEngineMemoryCell;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGSerialize2DGameFieldLayer extends NGUniplayObjectDefinition {

    protected CopyOnWriteArrayList<NGSerializeGameEngineMemoryCell> Cells;
    protected String Name;
    protected String Cellsclassname;
    protected CopyOnWriteArrayList<NGSerializePropertyItem> Props;

    public NGSerialize2DGameFieldLayer() {
        super();
    }

    public void setCells(CopyOnWriteArrayList<NGSerializeGameEngineMemoryCell> Value) {
        Cells = Value;
    }

    public CopyOnWriteArrayList<NGSerializeGameEngineMemoryCell> getCells() {
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

    public void setProps(CopyOnWriteArrayList<NGSerializePropertyItem> Value) {
        Props = Value;
    }

    public CopyOnWriteArrayList<NGSerializePropertyItem> getProps() {
        return Props;
    }

}
