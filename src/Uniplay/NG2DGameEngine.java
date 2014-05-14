package Uniplay;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Graphics.NG2DGraphicEngine;
import Uniplay.Kernel.NGGameEngine;

public class NG2DGameEngine extends NGGameEngine{

    protected NG2DGraphicEngine FGraphicEngine;

    @Override
    protected void DoCreateModules() {
        super.DoCreateModules();
        FGraphicEngine = (NG2DGraphicEngine)FModuleManager.newModule(NG2DGraphicEngine.class, "2DGraphicEngine");
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        FMemoryManager.addMemory("MAIN", 1, 16, 16);
        FMemoryManager.clearMemory("MAIN");
    }

    public NG2DGameEngine(NGUniplayObject aOwner) {
        super(aOwner);
    }

}
