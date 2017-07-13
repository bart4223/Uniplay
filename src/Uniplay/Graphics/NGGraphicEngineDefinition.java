package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineModuleDefinition;

import java.util.concurrent.CopyOnWriteArrayList;

public abstract class NGGraphicEngineDefinition extends NGGameEngineModuleDefinition {

    protected CopyOnWriteArrayList<NGGraphicEngineDefinitionRenderEngineItem> RenderEngines;
    protected CopyOnWriteArrayList<NGGraphicEngineDefinitionEventHandlerItem> EventHandlers;

    public  NGGraphicEngineDefinition() {
        super();
    }

    public void setRenderEngines(CopyOnWriteArrayList<NGGraphicEngineDefinitionRenderEngineItem> value) { RenderEngines = value;}
    public CopyOnWriteArrayList<NGGraphicEngineDefinitionRenderEngineItem> getRenderEngines() { return RenderEngines; }

    public void setEventHandlers(CopyOnWriteArrayList<NGGraphicEngineDefinitionEventHandlerItem> value) { EventHandlers = value;}
    public CopyOnWriteArrayList<NGGraphicEngineDefinitionEventHandlerItem> getEventHandlers() { return EventHandlers; }

}
