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
        for (NGRenderEngineItem item : FRenderEngines) {
            for (NGGameEngineMemoryCell cell : aContext.FCells) {
                if (item.getMemoryPage() == cell.getAddress().getPage()) {
                    NGRenderEngine rednerengine = item.getRenderEngine();
                    rednerengine.Cell = cell;
                    rednerengine.Render();
                }
            }
        }
    }

}
