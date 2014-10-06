package Uniplay.Physics;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.NGCustomGameObject;
import Uniwork.Base.NGObjectQueue;
import Uniwork.Base.NGObjectQueueManager;

public class NGObjectPhysicsProcessor extends NGUniplayComponent {

    protected NGObjectQueueManager FQueueManager;
    protected Integer FCurrentQueueID;
    protected NGObjectQueue FCurrentProcessQueue;
    protected NGObjectPhysicsBehaviourManager FBehaviourManager;

    protected String getCurrentQueueName() {
        return String.format("WORKER%d",FCurrentQueueID);
    }

    protected void DoBeforeExecute() {
        FCurrentProcessQueue = FQueueManager.getQueue(getCurrentQueueName());
        FCurrentQueueID = (FCurrentQueueID + 1) % 2;
    }

    protected void DoExecute() {

    }

    protected void DoAfterExecute() {
        FCurrentProcessQueue = null;
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FQueueManager.addQueue(String.format("WORKER%d", 1));
        FQueueManager.addQueue(String.format("WORKER%d",2));
    }

    public NGObjectPhysicsProcessor(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FQueueManager = new NGObjectQueueManager();
        FCurrentQueueID = 1;
        FCurrentProcessQueue = null;
        FBehaviourManager = null;
    }

    public synchronized void Execute() {
        DoBeforeExecute();
        try {
            if (FCurrentProcessQueue != null) {
                DoExecute();
            }
        }
        finally {
            DoAfterExecute();
        }
    }

    public void addQueue(NGGameObjectPhysicsActionItem aItem) {
        FQueueManager.enterQueue(getCurrentQueueName(), aItem);
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_PHYSICS, String.format("GameObject [%s] to PhysicsProcessor-Queue [%s] added.", aItem.toString(), getCurrentQueueName()));
    }

    public void setBehaviourManager(NGObjectPhysicsBehaviourManager aBehaviourManager) {
        FBehaviourManager = aBehaviourManager;
    }

    public NGObjectPhysicsBehaviourManager getBehaviourManager() {
        return FBehaviourManager;
    }

}
