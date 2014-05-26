package Uniplay.Graphics;

import Uniplay.Kernel.NGGameEngineModuleManager;

public class NG2DGraphicEngine extends NGGraphicEngine {

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        // ToDo
        //System.out.println(Definition != null);
    }

    public NG2DGraphicEngineDefinition Definition;

    public NG2DGraphicEngine(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
