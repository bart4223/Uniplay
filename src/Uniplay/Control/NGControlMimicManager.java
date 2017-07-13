package Uniplay.Control;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.Base.NGUniplayObjectRequestBroker;
import Uniplay.Misc.NGTaskManager;
import Uniplay.NGGameEngineConstants;
import Uniwork.Base.NGPropertyList;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGControlMimicManager extends NGUniplayComponent {

    protected CopyOnWriteArrayList<NGControlMimicItem> FMimics;
    protected NGTaskManager FTaskManager;
    protected NGUniplayObjectRequestBroker FORB;
    protected Boolean FInUpdate;

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

    protected void ActivateMimic(NGControlMimicItem aMimic, Boolean aValue, NGPropertyList aProps) {
        NGCustomControlMimic mimic = aMimic.getMimic();
        ActivateMimic(mimic.getKind(), aMimic, aValue, aProps);
    }

    protected void writeActivateLog(NGControlMimicItem aMimic, Boolean aValue) {
        if (aValue) {
            writeLog(String.format("Mimic [%s] activated.", aMimic.getMimic().getName()));
        }
        else {
            writeLog(String.format("Mimic [%s] deactivated.", aMimic.getMimic().getName()));
        }
    }

    protected void ActivateMimic(String aType, NGControlMimicItem aMimic, Boolean aValue, NGPropertyList aProps) {
        NGCustomControlMimic mimic = aMimic.getMimic();
        if (mimic.Type.equals(aType)) {
            if (aValue && aProps != null) {
                mimic.setProperties(mimic, aProps);
            }
            mimic.setActive(aValue);
            writeActivateLog(aMimic, aValue);
        }
    }

    protected void ActivateMimic(NGCustomControlMimic.Kind aKind, NGControlMimicItem aMimic, Boolean aValue, NGPropertyList aProps) {
        NGCustomControlMimic mimic = aMimic.getMimic();
        if (mimic.getKind() == aKind) {
            if (aValue && aProps != null) {
                mimic.setProperties(mimic, aProps);
            }
            mimic.setActive(aValue);
            writeActivateLog(aMimic, aValue);
        }
    }

    protected void DoInitialize() {
        super.DoInitialize();
        for (NGControlMimicItem item : FMimics) {
            item.Initialize();
        }
    }

    public NGControlMimicManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FMimics = new CopyOnWriteArrayList<NGControlMimicItem>();
        FInUpdate = false;
    }

    public NGControlMimicItem addMimic(NGCustomControlMimic aMimic) {
        NGControlMimicItem item = new NGControlMimicItem(aMimic);
        FMimics.add(item);
        return item;
    }

    public void ResetAllMimics() {
        FInUpdate = true;
        try {
            for (NGControlMimicItem item : FMimics) {
                resetMimic(item);
            }
        }
        finally {
            FInUpdate = false;
        }
    }

    public void ActivateMimics(NGCustomControlMimic.Kind aKind) {
        FInUpdate = true;
        try {
            for (NGControlMimicItem item : FMimics) {
                ActivateMimic(aKind, item, true, null);
            }
        }
        finally {
            FInUpdate = false;
        }
    }

    public void ActivateMimics(String aType) {
        FInUpdate = true;
        try {
            for (NGControlMimicItem item : FMimics) {
                ActivateMimic(aType, item, true, null);
            }
        }
        finally {
            FInUpdate = false;
        }
    }

    public void ActivateAllMimics() {
        FInUpdate = true;
        try {
            for (NGControlMimicItem item : FMimics) {
                ActivateMimic(item, true, null);
            }
        }
        finally {
            FInUpdate = false;
        }
    }

    public void DeactivateMimics(NGCustomControlMimic.Kind aKind) {
        FInUpdate = true;
        try {
            for (NGControlMimicItem item : FMimics) {
                ActivateMimic(aKind, item, false, null);
            }
        }
        finally {
            FInUpdate = false;
        }
    }

    public void DeactivateMimics(String aType) {
        FInUpdate = true;
        try {
            for (NGControlMimicItem item : FMimics) {
                ActivateMimic(aType, item, false, null);
            }
        }
        finally {
            FInUpdate = false;
        }
    }

    public void DeactivateAllMimics() {
        FInUpdate = true;
        try {
            for (NGControlMimicItem item : FMimics) {
                ActivateMimic(item, false, null);
            }
        }
        finally {
            FInUpdate = false;
        }
    }

    public void ActivateMimic(String aName) {
        ActivateMimic(aName, null);
    }

    public void ActivateMimic(NGControlMimicItem aItem) {
        ActivateMimic(aItem, null);
    }

    public void ActivateMimic(NGControlMimicItem aItem, NGPropertyList aProps) {
        ActivateMimic(aItem, true, aProps);
    }

    public void ActivateMimic(String aName, NGPropertyList aProps) {
        NGControlMimicItem item = getMimic(aName);
        ActivateMimic(item, true, aProps);
    }

    public void DeactivateMimic(String aName) {
        NGControlMimicItem item = getMimic(aName);
        ActivateMimic(item, false, null);
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

    public Boolean InUpdate() {
        return FInUpdate;
    }

}
