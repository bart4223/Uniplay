package Uniplay;

import Uniplay.Graphics.NG2DGraphicEngine;
import Uniplay.Kernel.NGGameEngine;

public class NG2DGameEngine extends NGGameEngine{

    protected NG2DGraphicEngine FGraphicEngine;

    @Override
    protected void DoCreateModules() {
        super.DoCreateModules();
        FGraphicEngine = new NG2DGraphicEngine(FModuleManager, "2D Graphic Engine");
        writeLog(String.format("Module [%s] created.",FGraphicEngine.getName()));
        FModuleManager.addModule(FGraphicEngine);
        writeLog(String.format("Module [%s] added.",FGraphicEngine.getName()));
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        FMemoryManager.addMemory("MAIN", 1, 16, 16);
        FMemoryManager.clearMemory("MAIN");
    }

    public NG2DGameEngine(Object aOwner) {
        super(aOwner);
    }

}
