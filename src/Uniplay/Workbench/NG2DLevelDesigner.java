package Uniplay.Workbench;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniplay.Kernel.NGGameEngineConstants;
import Uniplay.Storage.NG2DGameFieldSize;
import Uniplay.Storage.NG2DLevel;
import Uniplay.Storage.NG2DLevelManager;
import Uniwork.Base.NGObjectDeserializer;
import Uniwork.Base.NGObjectXMLDeserializerFile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class NG2DLevelDesigner extends NGUniplayComponent {

    protected Stage FStage;
    protected NG2DLevelDesignerStageController FStageController;
    protected String FImageName;
    protected Integer FGridSize;

    protected void CreateStage(){
        FStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("NG2DLevelDesignerStage.fxml"));
        try {
            lXMLLoader.load();
            FStageController = lXMLLoader.getController();
            FStageController.Designer = this;
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle("Uniplay.Workbench.LevelDesigner");
            FStage.setScene(new Scene(lRoot, 800, 800, Color.WHITE));
            FStage.setResizable(false);
        }
        catch( Exception e) {
            writeError("CreateStage", e.getMessage());
        }
    }

    protected NG2DLevelManager getLevelManager() {
        NG2DLevelManager levelManager = (NG2DLevelManager)ResolveObject(NGGameEngineConstants.CMP_2DLEVEL_MANAGER, NG2DLevelManager.class);
        return levelManager;
    }

    protected void showLevel(NG2DLevel aLevel) {
        FStageController.Level = aLevel;
        FStageController.RenderScene();
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        CreateStage();
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        FImageName = getConfigurationProperty("Imagename");
        FGridSize = Integer.parseInt(getConfigurationProperty("GridSize"));
    }

    public NG2DLevelDesigner(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FImageName = "";
        FGridSize = 1;
    }

    public void showStage() {
        FStage.show();
        FStageController.RenderScene();
    }

    public void setStagePosition(int aX, int aY) {
        FStage.setX(aX);
        FStage.setY(aY);
    }

    public NG2DLevel getDesignLevel() {
        NG2DLevelManager manager = getLevelManager();
        NG2DLevel level = manager.getLevel("DESIGN");
        if (level == null) {
            level = manager.addLevel("DESIGN", new NG2DGameFieldSize(0, 0));
        }
        return level;
    }

    public String getImageName() {
        return FImageName;
    }

    public Integer getGridSize() {
        return FGridSize;
    }

    public void loadFromGOF() {
        try
        {
            FileChooser fileChooser = new FileChooser();
            String userDirectoryString = System.getProperty("user.home");
            File userDirectory = new File(userDirectoryString);
            fileChooser.setInitialDirectory(userDirectory);
            fileChooser.setTitle("Load from GOF");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("GOF files (*.gof)", "*.gof");
            fileChooser.getExtensionFilters().add(extFilter);
            File chosenFile = fileChooser.showOpenDialog(FStage.getOwner());
            if (chosenFile != null) {
                NGObjectDeserializer Deserializer = new NGObjectXMLDeserializerFile(getDesignLevel(), chosenFile.getPath());
                Deserializer.setLogManager(getLogManager());
                Deserializer.deserializeObject();
                showLevel(getDesignLevel());
            }
            else {
                writeLog("Loading as GOF aborted...");
            }
        }
        catch (Exception e) {
            writeError("loadFromGOF", e.getMessage());
        }
    }

    public void setupDesignLevel() {
        NG2DLevelManager manager = getLevelManager();
        NG2DLevel level = manager.getLevel("DESIGN");
        if (level != null) {
            // ToDo Gamefield scannen
        }
    }

}
