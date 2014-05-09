package Uniplay;

import Uniplay.Kernel.NGGameEngine;

public class NG2DGameEngine extends NGGameEngine{

    @Override
    protected void CreateModules() {
        super.CreateModules();
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FMemoryManager.addMemory("MAIN", 1, 16, 16);
    }

    public NG2DGameEngine(Object aOwner) {
        super(aOwner);
    }

}
