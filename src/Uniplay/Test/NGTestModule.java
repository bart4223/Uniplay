package Uniplay.Test;

import Uniplay.Kernel.NGGameEngineConstants;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NGTestModule extends NGGameEngineModule {

    protected Stage FStage;
    protected WorkbenchController FController;

    protected void CreateWorkbenchStage(){
        FStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("Workbench.fxml"));
        try {
            lXMLLoader.load();
            FController = (WorkbenchController)lXMLLoader.getController();
            //FGameFieldController.Game = this;
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle("Workbench");
            FStage.setScene(new Scene(lRoot, 500, 500, Color.WHITE));
            FStage.setResizable(false);
            FStage.setX(300);
            FStage.setY(300);
        }
        catch( Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        //NGGameEngineMemoryManager manager = (NGGameEngineMemoryManager)ResolveObject(NGGameEngineConstants.CMP_MEMORY_MANAGER, NGGameEngineMemoryManager.class);
        //writeLog(manager.toString());
        //Object obj = ResolveObject("GameFieldController.Layer0", Canvas.class);
        //writeLog(obj.toString());
        CreateWorkbenchStage();
        FStage.show();
    }

    public NGTestModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
