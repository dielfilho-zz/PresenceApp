package models;

import java.util.Date;
import java.util.List;

/**
 * Created by Daniel Filho on 10/30/16.
 */

public class Presence {
    private String id;
    private String idTrainee;
    private String idTeam;
    private boolean valid;
    private int checks;
    private List<Integer> percents;
    private Date date;
    private boolean lastCheck;

    public boolean isLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(boolean lastCheck) {
        this.lastCheck = lastCheck;
    }

    public List<Integer> getPercents() {
        return percents;
    }

    public void setPercents(List<Integer> percents) {
        this.percents = percents;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdTrainee() {
        return idTrainee;
    }

    public void setIdTrainee(String idTrainee) {
        this.idTrainee = idTrainee;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getChecks() {
        return checks;
    }

    public void setChecks(int checks) {
        this.checks = checks;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
