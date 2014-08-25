package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObjectDefinition;
import Uniplay.Kernel.NGGameEngineEventHandler;
import Uniplay.Kernel.NGGameEngineModuleManager;

import java.lang.reflect.Constructor;

public class NG2DGraphicEngine extends NGGraphicEngine {

    @Override
    protected void registerEventHandlers() {
        super.registerEventHandlers();
        for (NGGraphicEngineDefinitionEventHandlerItem item : Definition.getEventHandlers()) {
            if (!item.Created) {
                try {
                    Class cl = NGUniplayObjectDefinition.class.getClassLoader().loadClass(item.getClassname());
                    Constructor<NGGameEngineEventHandler> cobj = cl.getConstructor(NG2DGraphicEngine.class);
                    if (cobj != null) {
                        NGGameEngineEventHandler obj = cobj.newInstance(this);
                        item.Created = obj != null;
                        if (item.Created) {
                            registerEventHandler(obj);
                        }
                    }
                } catch (Exception e) {
                    writeError("registerEventHandlers", e.getMessage());
                }
            }
        }
    }

    public NG2DGraphicEngine(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

    public void setViewPositions(double aX, double aY) {
        for (NGCustomRenderEngineItem item : getRenderEngineManager().getItems()) {
            if (item instanceof NG2DRenderEngineItem) {
                NG2DRenderEngineItem reitem = (NG2DRenderEngineItem)item;
                reitem.setViewPosition(aX, aY);
            }
        }
    }

}
