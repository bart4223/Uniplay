package Uniplay.Kernel;

import java.util.ArrayList;

public class NGGameEngineEventMemoryCellsChanged extends NGGameEngineEvent {

    protected ArrayList<NGGameEngineMemoryCell> FCells;
    protected NGGameEngineMemory FMemory;

    public NGGameEngineEventMemoryCellsChanged(Object source, NGGameEngineMemory aMemory, ArrayList<NGGameEngineMemoryCell> aCells) {
        super(source);
        FCells = new ArrayList<NGGameEngineMemoryCell>();
        for (NGGameEngineMemoryCell cell : aCells) {
            FCells.add(cell);
        }
        FMemory = aMemory;
    }

    public ArrayList<NGGameEngineMemoryCell> getCells() {
        return FCells;
    }

    public NGGameEngineMemory getMemory() {
        return FMemory;
    }

}
