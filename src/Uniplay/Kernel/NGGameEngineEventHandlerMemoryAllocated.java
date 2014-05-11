package Uniplay.Kernel;

public abstract class NGGameEngineEventHandlerMemoryAllocated extends NGGameEngineEventHandler {

    protected NGGameEngineMemory FMemory;

    @Override
    protected void BeforeHandleEvent() {
        super.BeforeHandleEvent();
        NGGameEngineEventMemoryAllocated event = (NGGameEngineEventMemoryAllocated)FEvent;
        FMemory = event.getMemory();
    }

    public NGGameEngineEventHandlerMemoryAllocated() {
        super(NGGameEngineMemory.EVT_MEMORY_ALLOCATED);
    }

}
