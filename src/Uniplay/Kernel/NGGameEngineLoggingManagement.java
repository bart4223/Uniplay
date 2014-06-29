package Uniplay.Kernel;

public interface NGGameEngineLoggingManagement {

    public void clearLog();
    public String getCompleteLog();
    public String getCompleteLog(String aFormat);
    public Boolean getShowLogEntrySource();

}
