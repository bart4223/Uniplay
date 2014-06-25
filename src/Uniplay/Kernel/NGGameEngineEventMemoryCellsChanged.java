package Uniplay.Kernel;

import java.util.ArrayList;

public class NGGameEngineEventMemoryCellsChanged extends NGGameEngineEvent {

    protected ArrayList<NGGameEngineMemoryCell> FCells;
    protected NGGameEngineMemory FMemory;

    public NGGameEngineEventMemoryCellsChanged(Object source, NGGameEngineMemory aMemory, ArrayList<NGGameEngineMemoryCell> aCells) {
        super(source);
        FMemory = aMemory;
        FCells = aCells;
    }

    public ArrayList<NGGameEngineMemoryCell> getCells() {
        return FCells;
    }

    public NGGameEngineMemory getMemory() {
        return FMemory;
    }

}
