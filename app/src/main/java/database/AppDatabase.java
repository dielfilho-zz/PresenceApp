package database;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Daniel Filho on 11/5/16.
 */

public class AppDatabase {

    private Context context;
    private Realm realm;
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if(instance == null)
            instance = new AppDatabase(context);

        instance.setContext(context);
        instance.initRealm();
        return instance;
    }

    private AppDatabase(Context context) {
        this.context = context;
        initRealm();
    }


    private void initRealm(){
        Realm.init(context);
        this.realm = Realm.getDefaultInstance();
    }

    public void createOrUpdate(Iterable<RealmObject> obj){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(obj);
        realm.commitTransaction();
        realm.close();
    }

    public void removeFromRealm(){

    }

    public void setContext(Context context) {
        this.context = context;
    }
}
