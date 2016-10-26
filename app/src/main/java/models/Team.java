package models;

import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Daniel Filho on 9/30/16.
 */

public class Team extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private String macAP;
    private Date dateInit;
    private Date dateEnd;
    private double distance;
    private double percent;
    private RealmList<Day> days;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMacAP() {
        return macAP;
    }

    public void setMacAP(String macAP) {
        this.macAP = macAP;
    }

    public Date getDateInit() {
        return dateInit;
    }

    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public RealmList<Day> getDays() {
        return days;
    }

    public void setDays(RealmList<Day> days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", macAP='" + macAP + '\'' +
                ", dateInit=" + dateInit +
                ", dateEnd=" + dateEnd +
                ", days=" + days +
                '}';
    }
}
