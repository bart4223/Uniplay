package Uniplay.Kernel;

import Uniplay.Base.NGUniplayObjectConfiguration;
import net.sf.json.JSONObject;


public class NGGameEngineConfiguration extends NGUniplayObjectConfiguration {

    @Override
    protected void DoAssignFrom(Object aObject) {
        if (aObject instanceof JSONObject) {
            JSONObject json = (JSONObject)aObject;
            DebugLevel = (Integer)json.get("debuglevel");
        }
    }

    public int DebugLevel = 0;

    public NGGameEngineConfiguration() {
        super();
    }

}
