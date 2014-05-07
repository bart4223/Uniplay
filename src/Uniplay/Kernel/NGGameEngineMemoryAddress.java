package Uniplay.Kernel;

import Uniplay.NGUniplayObject;

public class NGGameEngineMemoryAddress extends NGUniplayObject{

    protected int FPage;
    protected int FBase;
    protected int FOffset;

    public NGGameEngineMemoryAddress(int aPage, int aBase, int aOffset) {
        super();
        FPage = aPage;
        FBase = aBase;
        FOffset = aOffset;
    }

    public int getPage() {
        return FPage;
    }

    public int getBase() {
        return FBase;
    }

    public int getOffset() {
        return FOffset;
    }

}
