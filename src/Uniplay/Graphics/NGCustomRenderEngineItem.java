package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Kernel.NGGameEngineMemoryCell;
import Uniplay.Storage.NGCustomGameObject;
import Uniwork.Visuals.NGDisplayController;
import Uniwork.Visuals.NGDisplayView;

import java.util.ArrayList;

public abstract class NGCustomRenderEngineItem extends NGUniplayObject {

    protected NGCustomRenderEngine FRenderEngine;
    protected NGGameEngineMemoryCell FCell;
    protected NGCustomGameObject FGameObject;

    protected void DoInitialize() {
        FRenderEngine.Initialize();
    }

    protected void DoRender() {
        Boolean render = true;
        if (FCell != null) {
            if (FCell.getValueAsObject() instanceof NGRenderInformation) {
                NGRenderInformation ri = (NGRenderInformation)FCell.getValueAsObject();
                String name = ri.getResponsibleDisplayControllerName(this);
                render = name.length() > 0;
                if (render) {
                    FRenderEngine.setCurrentController(name);
                }
            }
            FRenderEngine.setProperty(FRenderEngine, "Cell", FCell);
        }
        else if (FGameObject != null) {
            FRenderEngine.setProperty(FRenderEngine, "GameObject", FGameObject);
        }
        if (render) {
            FRenderEngine.Render();
        }
    }

    public NGCustomRenderEngineItem(NGCustomRenderEngine aRenderEngine) {
        super();
        FRenderEngine = aRenderEngine;
        FCell = null;
        FGameObject = null;
    }

    @Override
    public Boolean setProperty(Object aObject, String aName, Object aValue) {
        Boolean res;
        Integer index = aName.indexOf(".");
        if (index >= 0) {
            String name = aName.substring(0, index);
            if (name.equals("RenderEngine")) {
                res = FRenderEngine.setProperty(FRenderEngine, aName.substring(index + 1, aName.length()), aValue);
            }
            else {
                res = super.setProperty(aObject, aName, aValue);
            }
        }
        else {
            res = super.setProperty(aObject, aName, aValue);
        }
        return res;
    }

    @Override
    public Object getProperty(Object aObject, String aName) {
        int index = aName.indexOf(".");
        if (index >= 0) {
            String name = aName.substring(0, index);
            if (name.equals("RenderEngine")) {
                return FRenderEngine.getProperty(FRenderEngine, aName.substring(index + 1, aName.length()));
            }
            else {
                return super.getProperty(aObject, aName);
            }
        }
        else {
            return super.getProperty(aObject, aName);
        }
    }

    public void Initialize() {
        DoInitialize();
    }

    public void Render() {
        DoRender();
    }

    public void setCell(NGGameEngineMemoryCell aCell) {
        FCell = aCell;
    }

    public NGGameEngineMemoryCell getCell() {
        return FCell;
    }

    public void setGameObject(NGCustomGameObject aGameObject) {
        FGameObject = aGameObject;
    }

    public NGCustomGameObject getGameObject() {
        return FGameObject;
    }

    public void addDisplayController(NGDisplayController aDisplayController) {
        FRenderEngine.addController(aDisplayController);
    }

    public NGDisplayController getDisplayController(String aName) {
        return FRenderEngine.getController(aName);
    }

    public NGDisplayController getCurrentDisplayControllers() {
        return FRenderEngine.getCurrentController();
    }

    public ArrayList<NGDisplayController> getDisplayControllers() {
        return FRenderEngine.getControllers();
    }

    public void setDisplayView(NGDisplayView aView) {
        FRenderEngine.setView(aView);
    }

    public NGDisplayView getDisplayView() {
        return FRenderEngine.getView();
    }

    public String getName() {
        return FRenderEngine.getName();
    }

}
