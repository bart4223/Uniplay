package Uniplay.Kernel;

import java.util.ArrayList;

public class NGGameEngineEventHandlerMemoryCellsChanged extends NGGameEngineEventHandler {

    protected ArrayList<NGGameEngineMemoryCell> FCells;

    @Override
    protected void BeforeHandleEvent() {
        super.BeforeHandleEvent();
        NGGameEngineEventMemoryCellsChanged event = (NGGameEngineEventMemoryCellsChanged)FEvent;
        FCells = event.getCells();
    }

    public NGGameEngineEventHandlerMemoryCellsChanged() {
        super(NGGameEngineMemory.EVT_MEMORY_CELLS_CHANGED);
    }

}
