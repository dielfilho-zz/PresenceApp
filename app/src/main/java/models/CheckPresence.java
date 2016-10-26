package models;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by Daniel Filho on 9/30/16.
 */

public class CheckPresence extends RealmObject {

    private String timeInit;
    private int duration;
    private boolean scheduled;

    public boolean isScheduled() {
        return scheduled;
    }

    public void setScheduled(boolean scheduled) {
        this.scheduled = scheduled;
    }

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
