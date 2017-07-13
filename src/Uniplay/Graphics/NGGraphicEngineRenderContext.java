package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniplay.Storage.NGCustomGameObject;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGGraphicEngineRenderContext extends NGUniplayObject {

    protected CopyOnWriteArrayList<NGGameEngineMemoryCell> FCells;
    protected NGCustomGameObject FGameObject;

    public NGGraphicEngineRenderContext(NGCustomGameObject aGameObject) {
        this(new CopyOnWriteArrayList<NGGameEngineMemoryCell>());
        FGameObject = aGameObject;
    }

    public NGGraphicEngineRenderContext(CopyOnWriteArrayList<NGGameEngineMemoryCell> aCells) {
        super();
        FCells = aCells;
        FGameObject = null;
    }

    public CopyOnWriteArrayList<NGGameEngineMemoryCell> getCells() {
        return FCells;
    }

    public NGCustomGameObject getGameObject() {
        return FGameObject;
    }

}
