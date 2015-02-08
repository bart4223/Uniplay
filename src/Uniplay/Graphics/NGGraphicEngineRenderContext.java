package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniplay.Storage.NGCustomGameObject;

import java.util.ArrayList;

public class NGGraphicEngineRenderContext extends NGUniplayObject {

    protected ArrayList<NGGameEngineMemoryCell> FCells;
    protected NGCustomGameObject FGameObject;

    public NGGraphicEngineRenderContext(NGCustomGameObject aGameObject) {
        this(new ArrayList<NGGameEngineMemoryCell>());
        FGameObject = aGameObject;
    }

    public NGGraphicEngineRenderContext(ArrayList<NGGameEngineMemoryCell> aCells) {
        super();
        FCells = aCells;
        FGameObject = null;
    }

    public ArrayList<NGGameEngineMemoryCell> getCells() {
        return FCells;
    }

    public NGCustomGameObject getGameObject() {
        return FGameObject;
    }

}
