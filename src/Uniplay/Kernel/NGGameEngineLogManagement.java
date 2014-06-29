package Uniplay.Kernel;

public interface NGGameEngineLogManagement {

    public void clearLog();
    public String getCompleteLog();
    public String getCompleteLog(String aFormat);
    public Boolean getShowLogEntrySource();

}
