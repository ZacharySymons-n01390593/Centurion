package home.control.centurion.LightControl;

public class DistanceSensor{
    private String startTime;
    private String endTime;

    public DistanceSensor(){

    }

    public String getEndTime() {
        return endTime;
    }
    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
