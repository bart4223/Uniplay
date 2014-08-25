package Uniplay.Graphics;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniplay.NGGameEngineConstants;

import java.util.ArrayList;

public class NGRenderEngineManager extends NGUniplayComponent {

    protected ArrayList<NGCustomRenderEngineItem> FRenderEngines;

    protected void DoInitializeRenderEngines() {
        for (NGCustomRenderEngineItem item : FRenderEngines) {
            item.Initialize();
        }
    }

    protected Integer DoRender(NGGraphicEngineRenderContext aContext, NGRenderEngine aRenderEngine, Integer aLayerIndex) {
        Integer count = 0;
        for (NGGameEngineMemoryCell cell : aContext.FCells) {
            if (aLayerIndex == cell.getAddress().getPage()) {
                aRenderEngine.Cell = cell;
                aRenderEngine.Render();
                count++;
            }
        }
        return count;
    }

    protected void DoRender(NGGraphicEngineRenderContext aContext) {
        Integer count = 0;
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_RENDERING, String.format("Render cells [%d] started...", aContext.getCells().size()));
        for (NGCustomRenderEngineItem item : FRenderEngines) {
            NGRenderEngine renderengine = item.getRenderEngine();
            count = count + DoRender(aContext, renderengine, item.getLayerIndex());
        }
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_RENDERING, String.format("Render cells [%d] finished.", count));
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        DoInitializeRenderEngines();
    }

    public NGRenderEngineManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FRenderEngines = new ArrayList<NGCustomRenderEngineItem>();
    }

    public void addItem(NG2DRenderEngineItem aItem) {
        FRenderEngines.add(aItem);
    }

    public void Render(NGGraphicEngineRenderContext aContext) {
        DoRender(aContext);
    }

    public ArrayList<NGCustomRenderEngineItem> getItems() {
        return FRenderEngines;
    }

    public NGRenderEngine getRenderEngine(String aName) {
        for(NGCustomRenderEngineItem item : FRenderEngines) {
            NGRenderEngine re = item.getRenderEngine();
            if (re.getName().equals(aName)) {
                return re;
            }
        }
        return null;
    }

}
