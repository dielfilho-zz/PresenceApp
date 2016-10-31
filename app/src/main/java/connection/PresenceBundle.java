package connection;

/**
 * Created by Daniel Filho on 10/29/16.
 */

public class PresenceBundle {

    private String idTeam;
    private String idTrainee;
    private double percent;

    public PresenceBundle() {
    }

    public PresenceBundle(String idTeam, String idTrainee, double percent) {
        this.idTeam = idTeam;
        this.idTrainee = idTrainee;
        this.percent = percent;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getIdTrainee() {
        return idTrainee;
    }

    public void setIdTrainee(String idTrainee) {
        this.idTrainee = idTrainee;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
