package Uniplay.Control;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObjectRegistration;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.NGCustomGame;
import Uniplay.Storage.NGGameManager;
import Uniwork.Base.NGPropertyList;

public class NGControlCenterModule extends NGGameEngineModule {

    protected NGControlMimicManager getControlMimicManager() {
        NGControlMimicManager manager = (NGControlMimicManager)ResolveObject(NGGameEngineConstants.CMP_CONTROLMIMIC_MANAGER, NGControlMimicManager.class);
        return manager;
    }

    protected NGGameManager getGameManager() {
        NGGameManager manager = (NGGameManager)ResolveObject(NGGameEngineConstants.CMP_GAME_MANAGER, NGGameManager.class);
        return manager;
    }

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        NGControlCenterDefinition cc = (NGControlCenterDefinition)FDefinition;
        for (NGControlCenterDefinitionMimicItem dmi : cc.getMimicItems()) {
            try {
                NGControlMimicManager mm = getControlMimicManager();
                Class mimiccl = getClass().getClassLoader().loadClass(dmi.getClassname());
                NGCustomGame game = getGameManager().getGame(dmi.getGame());
                NGCustomControlMimic mimic = (NGCustomControlMimic)mimiccl.getConstructor(NGControlMimicManager.class, NGCustomGame.class, String.class).newInstance(mm, game, dmi.getName());
                NGPropertyList props = new NGPropertyList();
                props.AssignFrom(dmi.getProps());
                mimic.setProperties(mimic, props);
                mm.addMimic(mimic);
            }
            catch (Exception e) {
                    writeError("BeforeInitialize", e.getMessage());
                }
            }
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
