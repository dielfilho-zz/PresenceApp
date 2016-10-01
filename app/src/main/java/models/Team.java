package models;

import java.util.Date;
import java.util.List;

/**
 * Created by Daniel Filho on 9/30/16.
 */

public class Team {
    private String id;
    private String name;
    private String macAP;
    private Date dateInit;
    private Date dateEnd;
    private List<Day> days;

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

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
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
