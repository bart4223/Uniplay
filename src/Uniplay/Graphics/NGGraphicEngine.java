package Uniplay.Graphics;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObjectDefinition;
import Uniplay.Kernel.NGGameEngineEventHandler;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;
import Uniwork.Base.NGSerializePropertyItem;
import Uniwork.Visuals.NGDisplayController;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;

import java.lang.reflect.Constructor;

public abstract class NGGraphicEngine extends NGGameEngineModule {

    public static void renderThread(final NGRenderEngineManager aManager, final NGGraphicEngineRenderContext aContext) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                aManager.Render(aContext);
            }
        });
    }

    protected void DoRender(NGGraphicEngineRenderContext aContext) {
        NGRenderEngineManager manager = getRenderEngineManager();
        renderThread(manager, aContext);
    }

    protected void DoRefresh() {
        getMemoryManager().refreshAllCells(NGGameEngineConstants.CMP_MAIN_MEMORY);
    }

    protected NGGameEngineMemoryManager getMemoryManager() {
        return (NGGameEngineMemoryManager)ResolveObject(NGGameEngineConstants.CMP_MEMORY_MANAGER, NGGameEngineMemoryManager.class);
    }

    @Override
    protected void registerEventHandlers() {
        super.registerEventHandlers();
        for (NGGraphicEngineDefinitionEventHandlerItem item : Definition.getEventHandlers()) {
            if (!item.Created) {
                try {
                    Class cl = NGUniplayObjectDefinition.class.getClassLoader().loadClass(item.getClassname());
                    Constructor<NGGameEngineEventHandler> cobj = cl.getConstructor(NGGraphicEngine.class);
                    if (cobj != null) {
                        NGGameEngineEventHandler obj = cobj.newInstance(this);
                        item.Created = true;
                        registerEventHandler(obj);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        NGRenderEngineManager manager = getRenderEngineManager();
        for (NGGraphicEngineDefinitionRenderEngineItem item : Definition.getRenderEngines()) {
            try {
                Class REcl = NG2DRenderEngine.class.getClassLoader().loadClass(item.getClassname());
                NG2DRenderEngine RE = (NG2DRenderEngine)REcl.getConstructor(String.class).newInstance(item.getName());
                Canvas canvas = (Canvas)ResolveObject(item.getLayername(), Canvas.class);
                NG2DRenderEngineItem REitem = new NG2DRenderEngineItem(RE, item.getLayerIndex(), canvas.getWidth(), canvas.getHeight());
                manager.addItem(REitem);
                writeLog(String.format("Render engine [%s] added.", RE.getName()));
                for (NGGraphicEngineDefinitionRenderEngineDisplayControllerItem dcitem : item.getDisplayControllers()) {
                    Class DCcl = NGDisplayController.class.getClassLoader().loadClass(dcitem.getClassname());
                    NGDisplayController dc = (NGDisplayController)DCcl.getConstructor(Canvas.class, String.class).newInstance(canvas, dcitem.getName());
                    dc.setPixelSize(dcitem.getPixelsize());
                    dc.setImageName(dcitem.getImagename());
                    for (NGSerializePropertyItem prop: dcitem.getProps()) {
                        dc.setProperty(dc, prop.getName(), prop.getValue());
                    }
                    for (NGGraphicEngineDefinitionRenderEngineDisplayControllerLayerItem layeritem : dcitem.Layers) {
                        dc.addLayer(layeritem.getName(), layeritem.getImagename());
                        writeLog(String.format("Layer [%s] for display controller [%s] for render engine [%s] added.", layeritem.getName(), dc.getName(), RE.getName()));
                    }
                    REitem.addDisplayController(dc);
                    writeLog(String.format("Display controller [%s] for render engine [%s] added.", dc.getName(), RE.getName()));
                }
                for (NGSerializePropertyItem prop: item.getProps()) {
                    REitem.setProperty(REitem, prop.getName(), prop.getValue());
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

    public void Refesh() {
        DoRefresh();
    }

}
