package Uniplay.Kernel;

import Uniplay.NGUniplayObject;

import java.util.ArrayList;

public class NGGameEngineMemory extends NGUniplayObject {

    protected int FSize;
    protected NGGameEngineMemoryManager FManager;
    protected ArrayList<NGGameEngineMemoryCell> FCells;

    protected NGGameEngineMemoryCell allocateCell(int aAddress) {
        NGGameEngineMemoryCell cell = new NGGameEngineMemoryCell(aAddress);
        FCells.add(cell);
        return cell;
    }

    protected void DoReallocate(int aSize) {
        // ToDo
    }

    protected void DoAllocate(int aSize) {
        double size;
        size = Math.pow(aSize, 3);
        for (int i = 0; i < size; i++) {
            allocateCell(i);
        }
        FSize = aSize;
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        DoAllocate(FSize);
    }

    public NGGameEngineMemory(NGGameEngineMemoryManager aManager) {
        this(aManager, 0);
    }

    public NGGameEngineMemory(NGGameEngineMemoryManager aManager, int aSize) {
        super();
        FManager = aManager;
        FCells = new ArrayList<NGGameEngineMemoryCell>();
        FSize = aSize;
    }

    public int getSize() {
        return FSize;
    }

    public int getAllocated() {
        return FCells.size();
    }

    public void Allocate(int aSize) {
        if (FSize != aSize) {
            if (FSize == 0) {
                DoAllocate(aSize);
            }
            else {
                DoReallocate(aSize);
            }
        }
    }

}
