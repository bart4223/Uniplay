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

    protected void DoRenderItem(NGCustomRenderEngineItem aItem, ArrayList<NGGameEngineMemoryCell> aCells) {
        for (NGGameEngineMemoryCell cell : aCells) {
            aItem.setCell(cell);
            aItem.Render();
        }
    }

    protected void DoRender(NGGraphicEngineRenderContext aContext) {
        for (NGCustomRenderEngineItem item : FRenderEngines) {
            NGRenderEngine re = item.getRenderEngine();
            writeLog(NGGameEngineConstants.DEBUG_LEVEL_RENDERING, String.format("Render engine [%s] start rendering...", re.getName()));
            ArrayList<NGGameEngineMemoryCell> cells = new ArrayList<NGGameEngineMemoryCell>();
            for (NGGameEngineMemoryCell cell : aContext.getCells()) {
                if (item.getLayerIndex() == cell.getAddress().getPage()) {
                    cells.add(cell);
                }
            }
            DoRenderItem(item, cells);
            writeLog(NGGameEngineConstants.DEBUG_LEVEL_RENDERING, String.format("Render engine [%s] [%d] cells rendered.", re.getName(), cells.size()));
        }
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
