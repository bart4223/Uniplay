package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniplay.Kernel.NGGameEngineMemoryCustomCellValue;
import Uniwork.Base.NGPropertyList;

import java.util.ArrayList;

public class NG2DGameFieldLayer extends NGUniplayObject {

    protected ArrayList<NGGameEngineMemoryCell> FCells;
    protected NGPropertyList FProps;
    protected String FName;
    protected NG2DGameField FGameField;

    public NG2DGameFieldLayer(NG2DGameField aGameField, String aName) {
        super();
        FGameField = aGameField;
        FName = aName;
        FCells = new ArrayList<NGGameEngineMemoryCell>();
        FProps = new NGPropertyList();
    }

    public String getName() {
        return FName;
    }

    public void clear() {
        FCells.clear();
        FProps.clear();
    }

    public NGGameEngineMemoryCell addCell(Integer aBase, Integer aOffset, Class aCellValueClass) {
        NG2DGameFieldSize size = getGameField().getSize();
        NGGameEngineMemoryCell cell = new NGGameEngineMemoryCell(FGameField.getLayers().indexOf(this), aBase, aOffset, aCellValueClass);
        FCells.add(cell);
        return cell;
    }

    public NG2DGameField getGameField() {
        return FGameField;
    }

    public int getCellCount() {
        return FCells.size();
    }

    public ArrayList<NGGameEngineMemoryCell> getCells() {
        return FCells;
    }

    public NGPropertyList getProps() {
        return FProps;
    }

    public void setProp(String aName, Object aValue) {
        FProps.set(aName, aValue);
    }

    public Object getProp(String aName) {
        return FProps.get(aName);
    }

}
