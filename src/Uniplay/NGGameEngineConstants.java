package Uniplay;

public abstract class NGGameEngineConstants {

    // --------------------- Debug ------------------------------------------
    // Level
    public final static Integer DEBUG_LEVEL_NONE                            = -1;
    public final static Integer DEBUG_LEVEL_DEFAULT                         = 0;
    public final static Integer DEBUG_LEVEL_MIMIC                           = 10;
    public final static Integer DEBUG_LEVEL_PHYSICS                         = 20;
    public final static Integer DEBUG_LEVEL_RENDERING                       = 30;
    public final static Integer DEBUG_LEVEL_MEMORY                          = 40;
    public final static Integer DEBUG_LEVEL_EVENTS                          = 50;

    // --------------------- Components -------------------------------------
    // Kernel
    public final static String CMP_KERNEL                                   = "Kernel";
    public final static String CMP_MODULE_MANAGER                           = "ModuleManager";
    public final static String CMP_TASK_MANAGER                             = "TaskManager";
    public final static String CMP_SPLASH_MANAGER                           = "SplashManager";
    public final static String CMP_EVENT_MANAGER                            = "EventManager";
    public final static String CMP_MEMORY_MANAGER                           = "MemoryManager";
    public final static String CMP_MEMORY_TRANSACTION_MANAGER               = "MemoryTransactionManager";
    public final static String CMP_MEMORY                                   = "Memory";
    public final static String CMP_MAIN_MEMORY                              = "Main";
    public final static String CMP_OBJECTREQUESTBROKER                      = "ObjectRequestBroker";
    // Data Storage
    public final static String CMP_STORAGE                                  = "Storage";
    public final static String CMP_2DLEVEL_MANAGER                          = "2DLevelManager";
    public final static String CMP_PLAYER_MANAGER                           = "PlayerManager";
    public final static String CMP_PLAYER_STATISTIC_MANAGER                 = "PlayerStatisticManager";
    public final static String CMP_GAME_MANAGER                             = "GameManager";
    public final static String CMP_GAME                                     = "Game";
    // Control Center
    public final static String CMP_CONTROL                                  = "Control";
    public final static String CMP_CONTROLMIMIC_MANAGER                     = "ControlMimicManager";
    // Sound System
    public final static String CMP_SOUND                                    = "Sound";
    public final static String CMP_SOUND_MANAGER                            = "SoundManager";
    // Graphic Engine
    public final static String CMP_GRAPHIC                                  = "Graphic";
    public final static String CMP_RENDERENGINE_MANAGER                     = "RenderEngineManager";
    // Physics Engine
    public final static String CMP_PHYSICS                                  = "Physics";
    public final static String CMP_PHYSICS_BEHAVIOUR_MANAGER                = "PhysicsBehaviourManager";
    public final static String CMP_PHYSICS_PROCESSOR                        = "PhysicsProcessor";
    // Debugger
    public final static String CMP_CONSOLE                                  = "Console";
    // Workbench
    public final static String CMP_WORKBENCH                                = "Workbench";
    public final static String CMP_WORKBENCH_MANAGER                        = "WorkbenchManager";
    public final static String CMP_WORKBENCH_LEVELDESIGNER_MANAGER          = "LevelDesignerManager";
    public final static String CMP_WORKBENCH_LEVELDESIGNER                  = "LevelDesigner";

    // --------------------- Events -----------------------------------------
    // Kernel
    public final static String EVT_KERNEL_INITIALIZED                       = String.format("%s.%s", CMP_KERNEL, "Initialized");
    public final static String EVT_KERNEL_STARTED                           = String.format("%s.%s", CMP_KERNEL, "Started");
    public final static String EVT_KERNEL_STOPPED                           = String.format("%s.%s", CMP_KERNEL, "Stopped");
    public final static String EVT_KERNEL_FINALIZED                         = String.format("%s.%s", CMP_KERNEL, "Finalized");
    public final static String EVT_KERNEL_SPLASH_RUN                        = String.format("%s.%s.%s", CMP_KERNEL, "Splash", "Run");
    public final static String EVT_KERNEL_SPLASH_FINISH                     = String.format("%s.%s.%s", CMP_KERNEL, "Splash", "Finish");
    // Memory
    public final static String EVT_MEMORY_ALLOCATED                         = String.format("%s.%s", CMP_MEMORY, "Allocated");
    public final static String EVT_MEMORY_CELLS_CHANGED                     = String.format("%s.%s.%s", CMP_MEMORY, "Cells", "Changed");
    // Storage
    public final static String EVT_GAME_START                               = String.format("%s.%s", CMP_GAME, "Start");
    public final static String EVT_GAME_BREAK                               = String.format("%s.%s", CMP_GAME, "Break");
    public final static String EVT_GAME_CONTINUE                            = String.format("%s.%s", CMP_GAME, "Continue");
    public final static String EVT_GAME_FINISH                              = String.format("%s.%s", CMP_GAME, "Finish");
    public final static String EVT_GAME_LEVEL_START                         = String.format("%s.%s.%s", CMP_GAME, "Level", "Start");
    public final static String EVT_GAME_LEVEL_FINISH                        = String.format("%s.%s.%s", CMP_GAME, "Level", "Finish");
    public final static String EVT_GAME_OBJECT_EXECUTED                     = String.format("%s.%s.%s", CMP_GAME, "Object", "Executed");
    public final static String EVT_GAME_OBJECT_POSITION_CHANGED             = String.format("%s.%s.%s.%s", CMP_GAME, "Object", "Position", "Changed");
    public final static String EVT_GAME_CHARACTER_POSITION_CHANGED          = String.format("%s.%s.%s.%s", CMP_GAME, "Character", "Position", "Changed");

    // --------------------- ORB --------------------------------------------
    // Mimic
    public final static String MIMIC_OBJECTREQUESTMETHOD_DEFAULT            = "Execute";
    public final static String MIMIC_OBJECTREQUEST_ACTION_TEMPLATE          = "Mimic.Action.%s";

    // --------------------- SOUNDS -----------------------------------------
    // Splash
    public final static String SOUND_SPLASH_SOURCE                          = "Source";

}
