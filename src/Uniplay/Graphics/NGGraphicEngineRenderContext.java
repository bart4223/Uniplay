package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineMemoryCell;

import java.util.ArrayList;

public class NGGraphicEngineRenderContext {

    protected ArrayList<NGGameEngineMemoryCell> FCells;
    public NGGraphicEngineRenderContext(ArrayList<NGGameEngineMemoryCell> aCells) {
        FCells = aCells;
    }

    public ArrayList<NGGameEngineMemoryCell> getCells() {
        return FCells;
    }

}
