package Uniplay.Storage;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Kernel.NGGameEngineMemoryCell;
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

    public void addCell(Integer aValue, Integer aBase, Integer aOffset) {
        NG2DGameFieldSize size = getGameField().getSize();
        NGGameEngineMemoryCell cell = new NGGameEngineMemoryCell(0, aBase, aOffset);
        cell.setValue(aValue);
        FCells.add(cell);
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

}
