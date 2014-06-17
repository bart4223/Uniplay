package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineModuleDefinition;

import java.util.ArrayList;

public abstract class NGGraphicEngineDefinition extends NGGameEngineModuleDefinition {

    protected ArrayList<NGGraphicEngineDefinitionRenderEngineItem> RenderEngines;

    public  NGGraphicEngineDefinition() {
        super();
    }

    public void setRenderEngines(ArrayList<NGGraphicEngineDefinitionRenderEngineItem> value) { RenderEngines = value;}
    public ArrayList<NGGraphicEngineDefinitionRenderEngineItem> getRenderEngines() { return RenderEngines; }

}
