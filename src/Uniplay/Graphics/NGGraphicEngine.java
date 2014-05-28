package Uniplay.Graphics;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Kernel.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;

public abstract class NGGraphicEngine extends NGGameEngineModule {

    protected void DoRender(NGGraphicEngineRenderContext aContext) {
        NGRenderEngineManager manager = getRenderEngineManager();
        manager.Render(aContext);
    }

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        registerEventHandler(new NGGraphicEngineEventHandlerMemoryCellsChanged(this));
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        // ToDo
        // Render Engines pro Canvas/Layer erzeugen
    }

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGUniplayComponent component = new NGRenderEngineManager(this, NGGameEngineConstants.CMP_RENDERENGINE_MANAGER);
        addSubComponent(component);
    }

    protected NGRenderEngineManager getRenderEngineManager() {
        return (NGRenderEngineManager)getSubComponent(NGGameEngineConstants.CMP_RENDERENGINE_MANAGER);
    }

    public NGGraphicEngine(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

    public NGGraphicEngineDefinition Definition;

    public void Render(NGGraphicEngineRenderContext aContext) {
        DoRender(aContext);
    }

}
