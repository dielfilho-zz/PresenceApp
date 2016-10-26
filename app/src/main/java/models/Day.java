package models;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Daniel Filho on 9/30/16.
 */
public class Day extends RealmObject{

    @PrimaryKey
    private String id;
    private String timeInit;
    private String timeEnd;
    private String name;
    private int idDay;
    private RealmList<CheckPresence> checkPresenceList;

    public RealmList<CheckPresence> getCheckPresenceList() {
        return checkPresenceList;
    }

    public void setCheckPresenceList(RealmList<CheckPresence> checkPresenceList) {
        this.checkPresenceList = checkPresenceList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeInit() {
        return timeInit;
    }

    public void setTimeInit(String timeInit) {
        this.timeInit = timeInit;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdDay() {
        return idDay;
    }

    public void setIdDay(int idDay) {
        this.idDay = idDay;
    }


    @Override
    public String toString() {
        return "Day{" +
                "id='" + id + '\'' +
                ", timeInit='" + timeInit + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                ", name='" + name + '\'' +
                ", idDay=" + idDay +
                ", checkPresenceList=" + checkPresenceList +
                '}';
    }
}
