package Uniplay.Kernel;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;

import java.util.ArrayList;

public class NGSplashManager extends NGUniplayComponent {

    protected ArrayList<NGSplashItem> FItems;
    protected Boolean FFinished;

    public NGSplashManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FItems = new ArrayList<NGSplashItem>();
        FFinished = false;
    }

    public void InitRun() {
        FFinished = false;
    }

    public void Run() {
        // Stage zeigen
        // ToDo
        for (NGSplashItem item : FItems) {
            item.Run();
        }
        FFinished = true;
    }

    public Boolean getFinished() {
        return FFinished;
    }

}
