package Uniplay.Sound;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObjectRegistration;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;
import Uniplay.NGGameEngineConstants;

public class NGSoundSystemModule extends NGGameEngineModule {

    protected NGSoundManager getSoundManager() {
        return (NGSoundManager)ResolveObject(NGGameEngineConstants.CMP_SOUND_MANAGER, NGSoundManager.class);
    }

    @Override
    protected void registerEventHandlers() {
        super.registerEventHandlers();
        registerEventHandler(new NGSoundSystemEventHandler(getSoundManager()));
    }

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGUniplayComponent component = new NGSoundManager(this, NGGameEngineConstants.CMP_SOUND_MANAGER);
        addSubComponent(component);
    }

    @Override
    protected void registerObjects() {
        super.registerObjects();
        NGUniplayObjectRegistration registration = (NGUniplayObjectRegistration) ResolveObject(NGUniplayObjectRegistration.class);
        registration.registerObject(NGGameEngineConstants.CMP_SOUND_MANAGER, getSoundManager());
    }

    @Override
    protected void BeforeInitialize() {
        super.BeforeInitialize();
        NGSoundManager sm = getSoundManager();
        for (NGSoundSystemDefinitionSoundItem item :  Definition.getSounds()) {
            NGSoundItem si = sm.addSound(item.getName(), item.getFilename());
            si.addEventname(item.Eventname);
        }
    }

    public NGSoundSystemModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

    public NGSoundSystemDefinition Definition;

}
