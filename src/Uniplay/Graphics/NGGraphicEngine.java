package Uniplay.Graphics;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Kernel.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;
import Uniwork.Visuals.NGDisplayController;
import javafx.scene.canvas.Canvas;

public abstract class NGGraphicEngine extends NGGameEngineModule {

    protected void DoRender(NGGraphicEngineRenderContext aContext) {
        NGRenderEngineManager manager = getRenderEngineManager();
        manager.Render(aContext);
    }

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        registerEventHandler(new NGGraphicEngineEventHandlerMemoryCellsChanged(this));
        NGRenderEngineManager manager = getRenderEngineManager();
        for (NGGraphicEngineDefintionRenderEngineItem item : Definition.getRenderEngines()) {
            try {
                Class REcl = NGRenderEngine.class.getClassLoader().loadClass(item.getClassname());
                NGRenderEngine RE = (NGRenderEngine)REcl.getConstructor(String.class).newInstance(item.getName());
                NGRenderEngineItem REitem = new NGRenderEngineItem(RE, item.getLayerIndex());
                manager.addItem(REitem);
                writeLog(String.format("Render engine [%s] added.", RE.getName()));
                Canvas canvas = (Canvas)ResolveObject(item.getLayername(), Canvas.class);
                for (NGGraphicEngineDefintionRenderEngineDisplayControllerItem dcitem : item.getDisplayControllers()) {
                    Class DCcl = NGDisplayController.class.getClassLoader().loadClass(dcitem.getClassname());
                    NGDisplayController dc = (NGDisplayController)DCcl.getConstructor(Canvas.class, String.class).newInstance(canvas, dcitem.getName());
                    REitem.getRenderEngine().addController(dc);
                    writeLog(String.format("Display controller [%s] for render engine [%s] added.", dc.getName(), RE.getName()));
                }
            }
            catch (Exception e) {
                writeError("BeforeInitialize", e.getMessage());
            }
        }
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
