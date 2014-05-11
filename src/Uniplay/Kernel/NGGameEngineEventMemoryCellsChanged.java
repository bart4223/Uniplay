package Uniplay.Kernel;

import java.util.ArrayList;

public class NGGameEngineEventMemoryCellsChanged extends NGGameEngineEvent {

    protected ArrayList<NGGameEngineMemoryCell> FCells;

    public NGGameEngineEventMemoryCellsChanged(Object source, ArrayList<NGGameEngineMemoryCell> aCells) {
        super(source);
        FCells = aCells;
    }

    public ArrayList<NGGameEngineMemoryCell> getCells() {
        return FCells;
    }

}
