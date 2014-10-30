package Uniplay.Physics;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.Misc.NGTaskCallback;
import Uniplay.Misc.NGTaskManager;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.NGCustomGameObject;
import Uniwork.Base.NGObjectQueue;
import Uniwork.Base.NGObjectQueueManager;

public class NGObjectPhysicsProcessor extends NGUniplayComponent {

    private class CallbackAddQueue implements NGTaskCallback {

        protected NGObjectPhysicsProcessor FProcessor;
        protected NGGameObjectPhysicsAction FAction;

        public CallbackAddQueue(NGObjectPhysicsProcessor aProcessor, NGGameObjectPhysicsAction aAction) {
            FProcessor = aProcessor;
            FAction = aAction;
        }

        @Override
        public void Call() {
            FProcessor.DoAddQueue(FAction);
        }
    }

    protected NGObjectQueueManager FQueueManager;
    protected Integer FCurrentQueueID;
    protected NGObjectQueue FCurrentProcessQueue;
    protected NGObjectPhysicsBehaviourManager FBehaviourManager;
    protected NGTaskManager FTaskManager;

    protected String getCurrentQueueName() {
        return String.format("WORKER%d", FCurrentQueueID);
    }

    protected void DoBeforeExecute() {
        FCurrentProcessQueue = FQueueManager.getQueue(getCurrentQueueName());
        FCurrentQueueID = FCurrentQueueID + 1;
        if (FCurrentQueueID > 2) {
            FCurrentQueueID = 1;
        }
    }

    protected NGTaskManager getTaskManager() {
        if (FTaskManager == null) {
            FTaskManager = (NGTaskManager)ResolveObject(NGGameEngineConstants.CMP_TASK_MANAGER, NGTaskManager.class);
        }
        return FTaskManager;
    }

    protected void DoExecute() {
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_PHYSICS, "PhysicsProcessor execute.");
        while (!FCurrentProcessQueue.isEmpty()) {
            NGGameObjectPhysicsAction GOaction = (NGGameObjectPhysicsAction)FCurrentProcessQueue.leave();
            NGObjectPhysicsBehaviourItem Bitem = FBehaviourManager.getItem((Class<NGCustomGameObject>)GOaction.getAffectedObject().getClass());
            for (NGObjectPhysicsPrincipleItem PPitem : Bitem.getPhysicsPrinciples()) {
                if (PPitem.getActive()) {
                    NGCustomPhysicsPrinciple pp = PPitem.getPrinciple();
                    if (pp.getAffectsByAction(GOaction.getPhysicsAction())) {
                        pp.setCurrentGOPhysicsAction(GOaction);
                        try {
                            pp.Execute();
                        }
                        finally {
                            pp.setCurrentGOPhysicsAction(null);
                        }
                    }
                }
            }
        }
    }

    protected void DoAfterExecute() {
        FCurrentProcessQueue = null;
    }

    protected void DoAddQueue(NGGameObjectPhysicsAction aAction) {
        FQueueManager.enterQueue(getCurrentQueueName(), aAction);
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_PHYSICS, String.format("GameObject [%s] to PhysicsProcessor-Queue [%s] added.", aAction.toString(), getCurrentQueueName()));
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FQueueManager.addQueue(String.format("WORKER%d", 1));
        FQueueManager.addQueue(String.format("WORKER%d", 2));
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

    public void addQueue(NGGameObjectPhysicsAction aAction, Integer aDelay) {
        if (aDelay > 0) {
            NGTaskManager tm = getTaskManager();
            tm.startSingularTask(new CallbackAddQueue(this, aAction), aDelay);
        }
        else {
            addQueue(aAction);
        }
    }

    public void addQueue(NGGameObjectPhysicsAction aAction) {
        DoAddQueue(aAction);
    }

    public void setBehaviourManager(NGObjectPhysicsBehaviourManager aBehaviourManager) {
        FBehaviourManager = aBehaviourManager;
    }

    public NGObjectPhysicsBehaviourManager getBehaviourManager() {
        return FBehaviourManager;
    }

}
