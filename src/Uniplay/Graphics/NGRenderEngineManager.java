package Uniplay.Graphics;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.NGCustomGameObject;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGRenderEngineManager extends NGUniplayComponent {

    protected CopyOnWriteArrayList<NGCustomRenderEngineItem> FRenderEngines;

    protected void DoInitializeRenderEngines() {
        for (NGCustomRenderEngineItem item : FRenderEngines) {
            item.Initialize();
        }
    }

    protected void DoRenderCells(NGCustomRenderEngineItem aItem, CopyOnWriteArrayList<NGGameEngineMemoryCell> aCells) {
        for (NGGameEngineMemoryCell cell : aCells) {
            aItem.setCell(cell);
            try {
                aItem.Render();
            }
            finally {
                aItem.setCell(null);
            }
        }
    }

    protected void DoRenderGameObject(NGCustomRenderEngineItem aItem, NGCustomGameObject aGameObject) {
        aItem.setGameObject(aGameObject);
        try {
            aItem.Render();
        }
        finally {
            aItem.setGameObject(null);
        }
    }

    protected void DoRender(NGGraphicEngineRenderContext aContext) {
        CopyOnWriteArrayList<NGGameEngineMemoryCell> cells = new CopyOnWriteArrayList<NGGameEngineMemoryCell>();
        for (NGCustomRenderEngineItem item : FRenderEngines) {
            if (aContext.getCells().size() > 0 ) {
                writeLog(NGGameEngineConstants.DEBUG_LEVEL_RENDERING, String.format("Render engine [%s] start cell rendering...", item.getName()));
                cells.clear();
                for (NGGameEngineMemoryCell cell : aContext.getCells()) {
                    if (cell.getValueAsObject() instanceof NGRenderInformation) {
                        NGRenderInformation ri = (NGRenderInformation)cell.getValueAsObject();
                        if (ri.IsRenderEngineResponsible(item)) {
                            cells.add(cell);
                        }
                    }
                }
                DoRenderCells(item, cells);
                writeLog(NGGameEngineConstants.DEBUG_LEVEL_RENDERING, String.format("Render engine [%s] [%d] cells rendered.", item.getName(), cells.size()));
            } else if (aContext.getGameObject() != null ) {
                writeLog(NGGameEngineConstants.DEBUG_LEVEL_RENDERING, String.format("Render engine [%s] start game object rendering...", item.getName()));
                if (item.getName().equals(aContext.getGameObject().getName())) {
                    DoRenderGameObject(item, aContext.getGameObject());
                }
                writeLog(NGGameEngineConstants.DEBUG_LEVEL_RENDERING, String.format("Render engine [%s] game object [%s] rendered.", item.getName(), aContext.getGameObject().getName()));
            }
        }
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        DoInitializeRenderEngines();
    }

    public NGRenderEngineManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FRenderEngines = new CopyOnWriteArrayList<NGCustomRenderEngineItem>();
    }

    public void addItem(NG2DRenderEngineItem aItem) {
        FRenderEngines.add(aItem);
    }

    public void Render(NGGraphicEngineRenderContext aContext) {
        DoRender(aContext);
    }

    public CopyOnWriteArrayList<NGCustomRenderEngineItem> getItems() {
        return FRenderEngines;
    }

    public NGCustomRenderEngineItem getRenderEngine(String aName) {
        for(NGCustomRenderEngineItem item : FRenderEngines) {
            if (item.getName().equals(aName)) {
                return item;
            }
        }
        return null;
    }

}
