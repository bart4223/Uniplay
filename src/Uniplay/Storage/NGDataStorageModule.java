package Uniplay.Storage;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObjectRegistration;
import Uniplay.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;

public class NGDataStorageModule extends NGGameEngineModule {

    protected NG2DLevelManager getLevelManager() {
        NG2DLevelManager manager = (NG2DLevelManager)ResolveObject(NGGameEngineConstants.CMP_2DLEVEL_MANAGER, NG2DLevelManager.class);
        return manager;
    }

    protected NGPlayerManager getPlayerManager() {
        NGPlayerManager manager = (NGPlayerManager)ResolveObject(NGGameEngineConstants.CMP_PLAYER_MANAGER, NGPlayerManager.class);
        return manager;
    }

    protected NGGameManager getGameManager() {
        NGGameManager manager = (NGGameManager)ResolveObject(NGGameEngineConstants.CMP_GAME_MANAGER, NGGameManager.class);
        return manager;
    }

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGUniplayComponent component = new NG2DLevelManager(this, NGGameEngineConstants.CMP_2DLEVEL_MANAGER);
        addSubComponent(component);
        component = new NGPlayerManager(this, NGGameEngineConstants.CMP_PLAYER_MANAGER);
        addSubComponent(component);
        component = new NGGameManager(this, NGGameEngineConstants.CMP_GAME_MANAGER);
        addSubComponent(component);
    }

    @Override
    protected void registerObjects() {
        super.registerObjects();
        NGUniplayObjectRegistration registration = (NGUniplayObjectRegistration)ResolveObject(NGUniplayObjectRegistration.class);
        registration.registerObject(NGGameEngineConstants.CMP_2DLEVEL_MANAGER, getLevelManager());
        registration.registerObject(NGGameEngineConstants.CMP_PLAYER_MANAGER, getPlayerManager());
        registration.registerObject(NGGameEngineConstants.CMP_GAME_MANAGER, getGameManager());
    }

    public NGDataStorageModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
