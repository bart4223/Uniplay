package Uniplay.Control;

import Uniplay.Base.NGUniplayObjectRequestBroker;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.NGCustomGame;
import Uniwork.Base.NGObjectRequestMethod;
import Uniwork.Base.NGObjectRequestObject;

public class NGControlMimicORBAction extends NGControlMimicSingleAction {

    protected NGObjectRequestObject FObjectRequestObject;
    protected NGObjectRequestMethod FObjectRequestMethod;

    protected void DoExecute() {
        writeLog(NGGameEngineConstants.DEBUG_LEVEL_MIMIC, String.format("Mimic-ORB-Action [%s.%s] executed.", FObjectRequestObject.getName(), FObjectRequestMethod.getName()));
    }

    protected NGUniplayObjectRequestBroker getObjectRequestBroker() {
        return FManager.getObjectRequestBroker();
    }

    protected void registerObject() {
        FObjectRequestObject = getObjectRequestBroker().registerObject(getORBObjectName(), this);
        FObjectRequestMethod = FObjectRequestObject.addMethod(NGGameEngineConstants.MIMIC_OBJECTREQUESTMETHOD_DEFAULT, NGGameEngineConstants.MIMIC_OBJECTREQUESTMETHOD_DEFAULT);
        writeLog(String.format("Mimic-ORB-Action [%s.%s] registered.", FObjectRequestObject.getName(), FObjectRequestMethod.getName()));
    }

    protected String getORBObjectName() {
        return String.format(NGGameEngineConstants.MIMIC_OBJECTREQUEST_ACTION_TEMPLATE, getName());
    }

    public NGControlMimicORBAction(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName, Kind.permant);
        registerObject();
    }

    public void Execute() {
        if (!InActivating()) {
            DoExecute();
        }
    }

    public NGObjectRequestObject getObjectRequestObject() {
        return FObjectRequestObject;
    }

    public NGObjectRequestMethod getObjectRequestMethod() {
        return FObjectRequestMethod;
    }

}
