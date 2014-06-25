package Uniplay.Kernel;

import java.util.ArrayList;

public class NGGameEngineEventHandlerMemoryCellsChanged extends NGGameEngineEventHandler {

    protected ArrayList<NGGameEngineMemoryCell> FCells;
    protected NGGameEngineMemory FMemory;

    @Override
    protected void BeforeHandleEvent() {
        super.BeforeHandleEvent();
        NGGameEngineEventMemoryCellsChanged event = (NGGameEngineEventMemoryCellsChanged)FEvent;
        FCells = event.getCells();
        FMemory = event.getMemory();
    }

    public NGGameEngineEventHandlerMemoryCellsChanged() {
        super(NGGameEngineConstants.EVT_MEMORY_CELLS_CHANGED);
    }

}
