package Uniplay.Control;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObjectRegistration;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;
import Uniplay.NGGameEngineConstants;

public class NGControlCenterModule extends NGGameEngineModule {

    protected NGControlMimicManager getControlMimicManager() {
        NGControlMimicManager manager = (NGControlMimicManager)ResolveObject(NGGameEngineConstants.CMP_CONTROLMIMIC_MANAGER, NGControlMimicManager.class);
        return manager;
    }

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGUniplayComponent component = new NGControlMimicManager(this, NGGameEngineConstants.CMP_CONTROLMIMIC_MANAGER);
        addSubComponent(component);
    }

    @Override
    protected void registerObjects() {
        super.registerObjects();
        NGUniplayObjectRegistration registration = (NGUniplayObjectRegistration) ResolveObject(NGUniplayObjectRegistration.class);
        registration.registerObject(NGGameEngineConstants.CMP_CONTROLMIMIC_MANAGER, getControlMimicManager());
    }

    public NGControlCenterModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
