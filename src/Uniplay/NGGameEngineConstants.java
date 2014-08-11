package Uniplay;

public abstract class NGGameEngineConstants {

    // --------------------- Debug ----------------------------------
    // Level
    public final static Integer DEBUG_LEVEL_NONE                    = -1;
    public final static Integer DEBUG_LEVEL_DEFAULT                 = 0;
    public final static Integer DEBUG_LEVEL_MIMIC                   = 10;
    public final static Integer DEBUG_LEVEL_RENDERING               = 20;
    public final static Integer DEBUG_LEVEL_EVENTS                  = 30;

    // --------------------- Components -----------------------------
    // Kernel
    public final static String CMP_OBJECTREQUESTBROKER              = "ObjectRequestBroker";
    public final static String CMP_KERNEL                           = "Kernel";
    public final static String CMP_MODULE_MANAGER                   = "ModuleManager";
    public final static String CMP_TASK_MANAGER                     = "TaskManager";
    public final static String CMP_SPLASH_MANAGER                   = "SplashManager";
    public final static String CMP_MEMORY_MANAGER                   = "MemoryManager";
    public final static String CMP_MEMORY_TRANSACTION_MANAGER       = "MemoryTransactionManager";
    public final static String CMP_MEMORY                           = "Memory";
    public final static String CMP_MAIN_MEMORY                      = "Main";
    // Data Storage
    public final static String CMP_2DLEVEL_MANAGER                  = "2DLevelManager";
    public final static String CMP_PLAYER_MANAGER                   = "PlayerManager";
    public final static String CMP_GAME_MANAGER                     = "GameManager";
    // Control Center
    public final static String CMP_CONTROLMIMIC_MANAGER             = "ControlMimicManager";
    // Graphic Engine
    public final static String CMP_RENDERENGINE_MANAGER             = "RenderEngineManager";
    // Debugger
    public final static String CMP_CONSOLE                          = "Console";
    // Workbench
    public final static String CMP_WORKBENCH_MANAGER                = "WorkbenchManager";
    public final static String CMP_WORKBENCH_LEVELDESIGNER_MANAGER  = "LevelDesignerManager";
    public final static String CMP_WORKBENCH_LEVELDESIGNER          = "LevelDesigner";

    // --------------------- Events ---------------------------------
    // Kernel
    public final static String EVT_KERNEL_INITIALIZED               = String.format("%s.%s", CMP_KERNEL, "Initialized");
    public final static String EVT_KERNEL_STARTED                   = String.format("%s.%s", CMP_KERNEL, "Started");
    public final static String EVT_KERNEL_STOPPED                   = String.format("%s.%s", CMP_KERNEL, "Stopped");
    public final static String EVT_KERNEL_FINALIZED                 = String.format("%s.%s", CMP_KERNEL, "Finalized");
    // Memory
    public final static String EVT_MEMORY_ALLOCATED                 = String.format("%s.%s", CMP_MEMORY, "Allocated");
    public final static String EVT_MEMORY_CELLS_CHANGED             = String.format("%s.%s.%s", CMP_MEMORY, "Cells", "Changed");

}
