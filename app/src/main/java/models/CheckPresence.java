package models;

/**
 * Created by Daniel Filho on 9/30/16.
 */

public class CheckPresence {

    private String timeInit;
    private int duration;

    public String getTimeInit() {
        return timeInit;
    }

    public void setTimeInit(String timeInit) {
        this.timeInit = timeInit;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "CheckPresence{" +
                "timeInit='" + timeInit + '\'' +
                ", duration=" + duration +
                '}';
    }
}
