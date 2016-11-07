package models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Daniel Filho on 10/31/16.
 */

public class PresencePendingIntent extends RealmObject{
    @PrimaryKey
    private long id;
    private long timeInMillis;


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }
}
