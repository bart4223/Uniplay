package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineModuleDefinition;

import java.util.ArrayList;

public abstract class NGGraphicEngineDefinition extends NGGameEngineModuleDefinition {

    protected ArrayList<NGGraphicEngineDefintionRenderEngineItem> RenderEngines;

    public  NGGraphicEngineDefinition() {
        super();
    }

    public void setRenderEngines(ArrayList<NGGraphicEngineDefintionRenderEngineItem> value) { RenderEngines = value;}
    public ArrayList<NGGraphicEngineDefintionRenderEngineItem> getRenderEngines() { return RenderEngines; }

}
