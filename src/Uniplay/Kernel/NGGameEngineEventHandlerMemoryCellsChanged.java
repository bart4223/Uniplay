package Uniplay.Kernel;

import Uniplay.NGGameEngineConstants;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGGameEngineEventHandlerMemoryCellsChanged extends NGGameEngineEventHandler {

    protected CopyOnWriteArrayList<NGGameEngineMemoryCell> FCells;
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
