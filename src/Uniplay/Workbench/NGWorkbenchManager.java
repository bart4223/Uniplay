package Uniplay.Workbench;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.Kernel.NGGameEngineConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NGWorkbenchManager extends NGUniplayComponent {

    protected Stage FControlStage;
    protected NGWorkbenchControlStageController FControlController;

    protected void CreateControlStage(){
        FControlStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("NGWorkbenchControlStage.fxml"));
        try {
            lXMLLoader.load();
            FControlController = lXMLLoader.getController();
            FControlController.Manager = this;
            Parent lRoot = lXMLLoader.getRoot();
            FControlStage.setTitle("Uniplay.Workbench.Control");
            FControlStage.setScene(new Scene(lRoot, 800, 50, Color.WHITE));
            FControlStage.setResizable(false);
        }
        catch( Exception e) {
            writeError("CreateWorkbenchStage", e.getMessage());
        }
    }

    protected void perfectLayout() {
        FControlStage.setX(500);
        FControlStage.setY(150);
    }

    protected NGLevelDesignerManager getLevelDesignerManager() {
        NGLevelDesignerManager manager = (NGLevelDesignerManager)ResolveObject(NGGameEngineConstants.CMP_WORKBENCH_LEVELDESIGNER_MANAGER, NGLevelDesignerManager.class);
        return manager;
    }

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        NGUniplayComponent component = new NGLevelDesignerManager(this, NGGameEngineConstants.CMP_WORKBENCH_LEVELDESIGNER_MANAGER);
        addSubComponent(component);
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        CreateControlStage();
    }

    public NGWorkbenchManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
    }

    public void showControlStage() {
        perfectLayout();
        FControlStage.show();
    }

    public void newLevelDesigner(String aName) {
        NG2DLevelDesigner designer = getLevelDesignerManager().addLevelDesigner(aName);
        designer.setStagePosition(500, 250);
    }

    public void showLevelDesigner(String aName) {
        NG2DLevelDesigner designer = getLevelDesignerManager().getLevelDesigner(aName);
        designer.showStage();
    }

}
