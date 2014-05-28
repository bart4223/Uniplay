package Uniplay.Graphics;

import Uniplay.Base.NGUniplayObject;

public class NGRenderEngineItem extends NGUniplayObject {

    public String FLayername;
    public int FMemoryPage;
    public NGRenderEngine FRenderEngine;

    public NGRenderEngineItem(String aLayername, int aMemoryPage, NGRenderEngine aRenderEngine) {
        super();
        FLayername = aLayername;
        FRenderEngine = aRenderEngine;
        FMemoryPage = aMemoryPage;
    }

    public String getLayername() {
        return FLayername;
    }

    public NGRenderEngine getRenderEngine() {
        return FRenderEngine;
    }

    public int getMemoryPage() {
        return FMemoryPage;
    }

}
