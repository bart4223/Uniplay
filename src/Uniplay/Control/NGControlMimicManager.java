package Uniplay.Control;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.Base.NGUniplayObjectRequestBroker;
import Uniplay.Kernel.NGTaskManager;
import Uniplay.NGGameEngineConstants;

import java.util.ArrayList;

public class NGControlMimicManager extends NGUniplayComponent {

    protected ArrayList<NGControlMimicItem> FMimics;
    protected NGTaskManager FTaskManager;
    protected NGUniplayObjectRequestBroker FORB;

    protected NGControlMimicItem getMimic(String aName) {
        for (NGControlMimicItem item : FMimics) {
            if (item.getMimic().getName().equals(aName)) {
                return item;
            }
        }
        return null;
    }

    protected void resetMimic(NGControlMimicItem aMimic) {
        aMimic.getMimic().reset();
    }

    protected void ActivateMimic(NGControlMimicItem aMimic, Boolean aValue) {
        ActivateMimic(aMimic.getMimic().getKind(), aMimic, aValue);
    }

    protected void ActivateMimic(NGCustomControlMimic.Kind aKind, NGControlMimicItem aMimic, Boolean aValue) {
        if (aMimic.getMimic().getKind() == aKind) {
            aMimic.getMimic().setActive(aValue);
            if (aValue) {
                writeLog(String.format("Mimic [%s] activated.", aMimic.getMimic().getName()));
            }
            else {
                writeLog(String.format("Mimic [%s] deactivated.", aMimic.getMimic().getName()));
            }
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

    public void ResetAllMimics() {
        for (NGControlMimicItem item : FMimics) {
            resetMimic(item);
        }
    }

    public void ActivateMimics(NGCustomControlMimic.Kind aKind) {
        for (NGControlMimicItem item : FMimics) {
            ActivateMimic(aKind, item, true);
        }
    }

    public void ActivateAllMimics() {
        for (NGControlMimicItem item : FMimics) {
            ActivateMimic(item, true);
        }
    }

    public void DeactivateMimics(NGCustomControlMimic.Kind aKind) {
        for (NGControlMimicItem item : FMimics) {
            ActivateMimic(aKind, item, false);
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

    public void resetMimic(String aName) {
        NGControlMimicItem item = getMimic(aName);
        resetMimic(item);
    }

    public NGTaskManager getTaskManager() {
        if (FTaskManager == null) {
            FTaskManager = (NGTaskManager)ResolveObject(NGGameEngineConstants.CMP_TASK_MANAGER, NGTaskManager.class);
        }
        return FTaskManager;
    }

    public NGUniplayObjectRequestBroker getObjectRequestBroker() {
        if (FORB == null) {
            FORB = (NGUniplayObjectRequestBroker)ResolveObject(NGGameEngineConstants.CMP_OBJECTREQUESTBROKER, NGUniplayObjectRequestBroker.class);
        }
        return FORB;
    }

}
