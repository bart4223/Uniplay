package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

import java.util.ArrayList;
import java.util.UUID;

public class NGGameEngineMemoryTransactionManager extends NGUniplayComponent {

    protected ArrayList<NGGameEngineMemoryTransaction> FTransactions;

    public NGGameEngineMemoryTransactionManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FTransactions = new ArrayList<NGGameEngineMemoryTransaction>();
    }

    public NGGameEngineMemoryTransaction BeginTransaction(NGGameEngineMemory aMemory) {
        NGGameEngineMemoryTransaction transaction = new NGGameEngineMemoryTransaction(aMemory);
        FTransactions.add(transaction);
        return transaction;
    }

    public void EndTransaction(UUID aGUID) {
        NGGameEngineMemoryTransaction transaction = getTransaction(aGUID);
        EndTransaction(transaction);
    }

    public void EndTransaction(NGGameEngineMemoryTransaction aTransaction) {
        FTransactions.remove(aTransaction);
    }

    public NGGameEngineMemoryTransaction getTransaction(UUID aGUID) {
        for (NGGameEngineMemoryTransaction transaction : FTransactions) {
            if (transaction.getGUID().equals(aGUID)) {
                return transaction;
            }
        }
        return null;
    }

    public void addCell(UUID aGUID, NGGameEngineMemoryCell aCell) {
        NGGameEngineMemoryTransaction transaction = getTransaction(aGUID);
        transaction.add(aCell);
    }

}
