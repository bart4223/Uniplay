package Uniplay.Kernel;

import Uniplay.NGUniplayObject;

import java.util.ArrayList;

public class NGGameEngineMemory extends NGUniplayObject {

    protected int FPageSize;
    protected int FBaseSize;
    protected int FOffsetSize;
    protected NGGameEngineMemoryManager FManager;
    protected ArrayList<NGGameEngineMemoryCell> FCells;

    protected NGGameEngineMemoryCell allocateCell(int aPage, int aBase, int aOffset) {
        NGGameEngineMemoryCell cell = new NGGameEngineMemoryCell(aPage, aBase, aOffset);
        FCells.add(cell);
        return cell;
    }

    protected void DoReallocate() {
        clear();
        DoAllocate();
    }

    protected void DoAllocate() {
        for (int page = 0; page < FPageSize; page++) {
            for (int base = 0; base < FBaseSize; base++) {
                for (int offset = 0; offset < FOffsetSize; offset++) {
                    allocateCell(page, base, offset);
                }
            }
        }
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        DoAllocate();
    }

    public NGGameEngineMemory(NGGameEngineMemoryManager aManager) {
        super();
        FManager = aManager;
        FCells = new ArrayList<NGGameEngineMemoryCell>();
        FPageSize = 0;
        FBaseSize = 0;
        FOffsetSize = 0;
    }

    public int getAllocated() {
        return FCells.size();
    }

    public void Reallocate(int aPageSize, int aBaseSize, int aOffsetSize) {
        if (FPageSize != aPageSize || FBaseSize != aBaseSize || FOffsetSize != aOffsetSize) {
            FPageSize = aPageSize;
            FBaseSize = aBaseSize;
            FOffsetSize = aOffsetSize;
            DoReallocate();
        }
    }

    public void clear() {
        for (NGGameEngineMemoryCell cell : FCells) {
            cell.clear();
        }
    }

    public int getAbsolutAddress(NGGameEngineMemoryAddress aAddress) {
        return (aAddress.getPage() * FBaseSize * FOffsetSize) + (aAddress.getBase() * FOffsetSize) + aAddress.getOffset();
    }

}
