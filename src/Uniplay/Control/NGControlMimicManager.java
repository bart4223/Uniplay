package Uniplay.Control;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.Kernel.NGTaskManager;
import Uniplay.NGGameEngineConstants;
import Uniwork.Base.NGObjectRequestBroker;

import java.util.ArrayList;

public class NGControlMimicManager extends NGUniplayComponent {

    protected ArrayList<NGControlMimicItem> FMimics;
    protected NGTaskManager FTaskManager;
    protected NGObjectRequestBroker FORB;

    protected NGControlMimicItem getMimic(String aName) {
        for (NGControlMimicItem item : FMimics) {
            if (item.getMimic().getName().equals(aName)) {
                return item;
            }
        }
        return null;
    }

    protected void ActivateMimic(NGControlMimicItem aMimic, Boolean aValue) {
        aMimic.getMimic().setActive(aValue);
        if (aValue) {
            writeLog(String.format("Mimic [%s] activated.", aMimic.getMimic().getName()));
        }
        else {
            writeLog(String.format("Mimic [%s] deactivated.", aMimic.getMimic().getName()));
        }
    }

    public NGControlMimicManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FMimics = new ArrayList<NGControlMimicItem>();
    }

    public NGControlMimicItem addMimic(NGCustomControlMimic aMimic) {
        NGControlMimicItem item = new NGControlMimicItem(aMimic);
        FMimics.add(item);
        return item;
    }

    public void ActivateAllMimics() {
        for (NGControlMimicItem item : FMimics) {
            ActivateMimic(item, true);
        }
    }

    public void DeactivateAllMimics() {
        for (NGControlMimicItem item : FMimics) {
            ActivateMimic(item, false);
        }
    }

    public void ActivateMimic(String aName) {
        NGControlMimicItem item = getMimic(aName);
        ActivateMimic(item, true);
    }

    public void DeactivateMimic(String aName) {
        NGControlMimicItem item = getMimic(aName);
        ActivateMimic(item, false);
    }

    public NGTaskManager getTaskManager() {
        if (FTaskManager == null) {
            FTaskManager = (NGTaskManager)ResolveObject(NGGameEngineConstants.CMP_TASK_MANAGER, NGTaskManager.class);
        }
        return FTaskManager;
    }

    public NGObjectRequestBroker getObjectRequestBroker() {
        if (FORB == null) {
            FORB = (NGObjectRequestBroker)ResolveObject(NGGameEngineConstants.CMP_OBJECTREQUESTBROKER, NGObjectRequestBroker.class);
        }
        return FORB;
    }

}
