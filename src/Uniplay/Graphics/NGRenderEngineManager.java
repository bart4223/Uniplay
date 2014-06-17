package Uniplay.Graphics;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.Kernel.NGGameEngineMemoryCell;

import java.util.ArrayList;

public class NGRenderEngineManager extends NGUniplayComponent {

    protected ArrayList<NGRenderEngineItem> FRenderEngines;

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        for (NGRenderEngineItem item : FRenderEngines) {
            item.getRenderEngine().Initialize();
        }
    }

    public NGRenderEngineManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FRenderEngines = new ArrayList<NGRenderEngineItem>();
    }

    public void addItem(NGRenderEngineItem aItem) {
        FRenderEngines.add(aItem);
    }

    public void Render(NGGraphicEngineRenderContext aContext) {
        int i = 0;
        writeLog(5, String.format("Render cells [%d] started...", aContext.getCells().size()));
        for (NGRenderEngineItem item : FRenderEngines) {
            NGRenderEngine renderengine = item.getRenderEngine();
            for (NGGameEngineMemoryCell cell : aContext.FCells) {
                if (item.getLayerIndex() == cell.getAddress().getPage()) {
                    renderengine.Cell = cell;
                    renderengine.Render();
                    i++;
                }
            }
        }
        writeLog(5, String.format("Render cells [%d] finished.", i));
    }

}
