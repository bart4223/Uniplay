package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineMemory;
import Uniplay.Kernel.NGGameEngineMemoryCell;

import java.util.ArrayList;

public class NGGraphicEngineRenderContext {

    protected ArrayList<NGGameEngineMemoryCell> FCells;
    protected NGGameEngineMemory FMemory;

    public NGGraphicEngineRenderContext(NGGameEngineMemory aMemory, ArrayList<NGGameEngineMemoryCell> aCells) {
        FCells = aCells;
        FMemory = aMemory;
    }

    public ArrayList<NGGameEngineMemoryCell> getCells() {
        return FCells;
    }

    public NGGameEngineMemory getFMemory() {
        return FMemory;
    }

}
